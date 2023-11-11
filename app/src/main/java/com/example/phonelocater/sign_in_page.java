package com.example.phonelocater;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Document;

import java.util.HashMap;
import java.util.Map;


public class sign_in_page extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_page);
        //Firebase Database
    FirebaseFirestore firestore;
    firestore=FirebaseFirestore.getInstance();


//        Map<String,Object> users=new HashMap<>();
//        users.put("FirstName","Vinayak");
//        users.put("LastName","Teli");
//        users.put("Mobile","7249194101");
//        firestore.collection("users").add(users).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//            @Override
//            public void onSuccess(DocumentReference documentReference) {
//                Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_LONG).show();
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(getApplicationContext(),"Fail",Toast.LENGTH_LONG).show();
//
//            }
//        });


        TextView name=findViewById(R.id.name);
        TextView phone=findViewById(R.id.phone);
        TextView username=findViewById(R.id.username);
        TextView password=findViewById(R.id.password);
        TextView login_re = findViewById(R.id.login);
        Button sign1_btn =findViewById(R.id.sign_data);

        //login page
       login_re.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(sign_in_page.this,MainActivity.class);

               startActivity(intent);
           }
       });


       //create a account for user
        sign1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Write a message to the database



                //store values in variable
                String name1= name.getText().toString();
                String phone1= phone.getText().toString();
                String username1=username.getText().toString();
                String password1=password.getText().toString();
                Myhelper helper= new Myhelper(name1,phone1,username1,password1);

//                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\.+[a-z]+";

                if(name1.equals("")&&phone1.equals("")&&username1.equals("")&&password1.equals("")){
                    Toast.makeText(sign_in_page.this,"PLEASE ENTER THE FOLLOWING FIELDS!!",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(phone1.length()>10|| phone1.length()<0 ||phone1.length()<10) {
                        Toast.makeText(sign_in_page.this, "Required 10 digit ", Toast.LENGTH_SHORT).show();

                    }


//                    if (!username1.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(username1).matches()) {
//                        Toast.makeText(sign_in_page.this, "Enter valid Email address ", Toast.LENGTH_SHORT).show();
//                    }
                    if(!Patterns.EMAIL_ADDRESS.matcher(username1).matches()){
                        Toast.makeText(sign_in_page.this, "Enter valid Email address ", Toast.LENGTH_SHORT).show();
                    }
                    else {

//                         else {
//                            Toast.makeText(sign_in_page.this, "valid !", Toast.LENGTH_SHORT).show();


                            Map<String, Object> users = new HashMap<>();
                            users.put("FirstName", name1);
                            users.put("Phone", phone1);
                            users.put("Username", username1);
                            users.put("Password", password1);

                            mAuth = FirebaseAuth.getInstance();
                            mAuth.createUserWithEmailAndPassword(username1, password1);

                            firestore.collection("users").add(users).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_LONG).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_LONG).show();

                                }
                            });


                            Toast.makeText(sign_in_page.this, "ACCOUNT CREATED  SUCCESSFULLY!!", Toast.LENGTH_SHORT).show();

                        Intent intent2   = new Intent(sign_in_page.this,MainActivity2.class);

                        startActivity(intent2);
                            name.setText(null);
                            phone.setText(null);
                            username.setText(null);
                            password.setText(null);


                        }
                    }

                }
//            }

        });



    }



}