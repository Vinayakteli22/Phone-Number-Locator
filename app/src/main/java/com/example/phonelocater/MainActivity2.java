package com.example.phonelocater;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;

import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.TelephonyManager;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity2 extends AppCompatActivity {
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    private static final int PHONE_NUMBER_HINT = 100;
    private final int PERMISSION_REQ_CODE = 200;
    public  static  final int CONTACT_PERMISSION_CODE=1;
    database g;
    // string
    String phonenumber;
    String contactname;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        EditText main_edit = findViewById(R.id.phone_main);
        Button main_btn = findViewById(R.id.Button_main);
        TextView  textView1 =  findViewById(R.id.result);

        /*androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);




        //navigation
        // drawer layout instance to toggle the menu icon to open
        // drawer and back button to close drawer
        drawerLayout = findViewById(R.id.navbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        // to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/



       /* NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_logout:
                        // Sign out the user using Firebase Authentication
                        Toast.makeText(MainActivity2.this, "hello", Toast.LENGTH_SHORT).show();
                        FirebaseAuth.getInstance().signOut();

                        // Redirect the user to the login page
                        Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                        startActivity(intent);
                        finish(); // Prevents the user from navigating back to the MainActivity
                        break;
                    // Add more cases for other menu items
                }
                return false;
            }
        });*/

//python
        if (! Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }
     // instance
        Python py=Python.getInstance();
        PyObject pyobj=py.getModule("hellow");
        g=new database(this);



        //contact name
        if (ContextCompat.checkSelfPermission(MainActivity2.this,
                Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity2.this,
                    new String[]{Manifest.permission.READ_CONTACTS},CONTACT_PERMISSION_CODE);
        }


        main_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                    if (main_edit.getText().toString().trim().equals("")) {
                    Toast.makeText(MainActivity2.this, "Enter the Phone Number", Toast.LENGTH_SHORT).show();
                } else {
                    if (main_edit.length() !=13) {
                        Toast.makeText(MainActivity2.this, "Required phone number with country code ", Toast.LENGTH_SHORT).show();
                    }

                    else {
                        //Toast.makeText(MainActivity2.this, "SUBMIT  ", Toast.LENGTH_SHORT).show();
                      //main_edit.setText(null);

                        phonenumber = main_edit.getText().toString();


                                contactname = getContactName(MainActivity2.this, phonenumber);
                                if (contactname != null) {


                                    //Name found in contacts or database
                                    android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(MainActivity2.this);
                                    builder.setTitle(R.string.app_name);
                                    builder.setMessage("\nName=" + contactname + " \nIt is correct Name!!!!!");

                                    builder.setCancelable(false)
                                            .setPositiveButton("Yes", (dialog, id) -> {
                                                boolean i = g.insert_data(contactname, phonenumber);
                                                if (i == true) {
                                                    Toast.makeText(MainActivity2.this, "Successful", Toast.LENGTH_SHORT).show();
                                                } else {
                                                    Toast.makeText(MainActivity2.this, " not Successful", Toast.LENGTH_SHORT).show();
                                                }
                                                dialog.cancel();
                                            })
                                            .setNegativeButton("No", (dialog, id) -> {
                                                // Show "Do you want to save it?" dialog box
                                                android.app.AlertDialog.Builder builder1 = new android.app.AlertDialog.Builder(MainActivity2.this);
                                                builder1.setTitle(R.string.app_name);
                                                builder1.setMessage("Do you want to save it!!!!!");

                                                builder1.setCancelable(false)
                                                        .setPositiveButton("Yes", (dialog1, id1) -> {
                                                            Intent i1 = new Intent(MainActivity2.this, savename.class);
                                                            startActivity(i1);
                                                        })
                                                        .setNegativeButton("No", (dialog12, id12) -> dialog12.cancel());
                                                android.app.AlertDialog alert = builder1.create();
                                                alert.show();
                                            });
                                    android.app.AlertDialog alert = builder.create();
                                    alert.show();

                                } else
                                {
                                    // Name not found in contacts or database
                                    android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(MainActivity2.this);
                                    builder.setTitle(R.string.app_name);
                                    builder.setMessage("Name not found!!!");

                                    builder.setCancelable(false)
                                            .setPositiveButton("OK", (dialog, id) -> {
                                                dialog.cancel();
                                            });
                                    android.app.AlertDialog alert = builder.create();
                                    alert.show();



                                }



                        PyObject obj=pyobj.callAttr("main",main_edit.getText().toString());
                        textView1.setText(obj.toString());

                        }
                }
        }
    });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public String getContactName(Context context, String phoneNumber) {
        ContentResolver cr = context.getContentResolver();
        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phoneNumber));
        try (Cursor cursor = cr.query(uri, new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME}, null, null, null)) {
            if (cursor != null && cursor.moveToFirst()) {
                // Name found in contacts, return it
                return cursor.getString(0);
            } else {
                // Name not found in contacts, search in database
                database g = new database(context);
                String name = g.searchNameByNumber(phoneNumber);
                if (name != null) {
                    // Name found in database, return it
                    return name;
                } else {
                    // Name not found in contacts or database, return null
                    return null;
                }
            }


        }


    }

}