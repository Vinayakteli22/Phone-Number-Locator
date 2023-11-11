package com.example.phonelocater;

import static com.google.firebase.auth.FirebaseAuth.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.auth.FirebaseAuthCredentialsProvider;
//import com.google.firebase.ktx.Firebase;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView username=findViewById(R.id.username);
        TextView password=findViewById(R.id.password);
        Button login_btn =findViewById(R.id.login_btn);
        Button sign_btn =findViewById(R.id.signin_btn);
        ImageView fb=findViewById(R.id.facebook);
        ImageView gog=findViewById(R.id.google);
        ImageView twit=findViewById(R.id.twitter);
        TextView fpass=findViewById(R.id.forgetpass);


        //username=roy
        //password=2004
        //forget password
        fpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Temporary Unavailable",Toast.LENGTH_SHORT).show();
            }
        });

        //social media login
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Coming Ready Soon",Toast.LENGTH_SHORT).show();
            }
        });
gog.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Toast.makeText(MainActivity.this,"Coming Ready Soon",Toast.LENGTH_SHORT).show();
    }
});
        twit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Coming Ready Soon",Toast.LENGTH_SHORT).show();
            }
        });
  //login button
        if (SharedPrefManager.isLoggedIn(getApplicationContext())) {
            // if logged in, start home activity
            Intent intent1 = new Intent(MainActivity.this, card.class);
            startActivity(intent1);
            finish();
        } else {
            // if not logged in, continue with login activity
            // ...


            login_btn.setOnClickListener(new View.OnClickListener() {

// save login info
                @Override
                public void onClick(View view) {
                    String uname = username.getText().toString();
                    String upass = password.getText().toString();

                    FirebaseFirestore firestore;
                    firestore = FirebaseFirestore.getInstance();
                    FirebaseAuth firebaseAuth = getInstance();
                    mAuth = FirebaseAuth.getInstance();

                    Map<String, Object> users = new HashMap<>();

                    mAuth.signInWithEmailAndPassword(uname, upass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getApplicationContext(), "Login successful!!", Toast.LENGTH_LONG).show();
                                        username.setText(null);
                                        password.setText(null);

                                        // save the user's login status and username
                                        SharedPrefManager.setLoggedIn(getApplicationContext(), true, uname);

                                        // if sign-in is successful, intent to home activity
                                        Intent intent1 = new Intent(MainActivity.this, MainActivity2.class);
                                        startActivity(intent1);
                                    } else {
                                        // sign-in failed
                                        Toast.makeText(getApplicationContext(), "Login failed!!", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }


            });
        }
//sign in button
        sign_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,sign_in_page.class);

                startActivity(intent);
            }
        });

    }


}