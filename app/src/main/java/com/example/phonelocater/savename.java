package com.example.phonelocater;



import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class savename extends AppCompatActivity {
    EditText name;
    EditText number;
    ImageView save;
    database g;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_savename);
        name=findViewById(R.id.edit_save_name);
        number=findViewById(R.id.edit_save_number);
        save=findViewById(R.id.image_save_);

        g=new database(this);


        save.setOnClickListener(view -> {
            String name1=name.getText().toString();
            String number1=number.getText().toString();
            if(name.getText().toString().equals(" ")&&number.getText().toString().equals(" ")){
                Toast.makeText(savename.this, "Enter the following fields", Toast.LENGTH_SHORT).show();
            }
            else{
                if(number1.length() != 13&& number1.length()<0){
                    Toast.makeText(savename.this, "Enter the number with country code ", Toast.LENGTH_SHORT).show();

                }
                else{
                    boolean result=g.insert_data(name1,number1);
                    if(result==true){
                        Toast.makeText(savename.this, "Successful", Toast.LENGTH_SHORT).show();
                        name.setText("");
                        number.setText("");

                    }
                    else
                    {
                        Toast.makeText(savename.this, " not Successful", Toast.LENGTH_SHORT).show();

                    }
                }
            }

        });



    }
}