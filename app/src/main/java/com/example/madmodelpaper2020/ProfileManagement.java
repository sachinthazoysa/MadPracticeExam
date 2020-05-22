package com.example.madmodelpaper2020;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.madmodelpaper2020.database.DBHelper;

public class ProfileManagement extends AppCompatActivity {
    Button register, male,female;
    DBHelper dbHelper;
    EditText name,password,birth,gender;
    private RadioGroup radioSexGroup;
    private RadioButton radioSexButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_management);


        register=findViewById(R.id.updateR);
        name = findViewById(R.id.editusernameR);
        password = findViewById(R.id.editPasswordR);
        birth = findViewById(R.id.edithBirthdayR);
        final String gender = "male";



        register.setOnClickListener(new View.OnClickListener() {
            String Name= name.getText().toString();
            String Password= password.getText().toString();
            String Birth= birth.getText().toString();
            String Gender= gender;

            @Override
            public void onClick(View v) {
               if(dbHelper.addInfo(Name, Birth, Password, Gender)){
                   Toast.makeText(ProfileManagement.this, "Added successfully", Toast.LENGTH_SHORT).show();
               }else{
                   Toast.makeText(ProfileManagement.this, "Error", Toast.LENGTH_SHORT).show();
               }
            }
        });




    }



}



