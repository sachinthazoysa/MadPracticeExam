package com.example.madmodelpaper2020;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.madmodelpaper2020.database.DBHelper;

public class EditProfile extends AppCompatActivity {
    Button searchBtn,updateBtn;
    DBHelper dbHelper;
    EditText userName, Password, Birth, Gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        searchBtn=findViewById(R.id.search);
        updateBtn=findViewById(R.id.editE);
        userName=findViewById(R.id.editusernameR);
        Password=findViewById(R.id.editPasswordR);
        Birth=findViewById(R.id.edithBirthdayR);
        final String gender= "female";

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.readAllInfo();
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            String name = userName.getText().toString();
            String birth = Birth.getText().toString();
            String password = Password.getText().toString();

            @Override
            public void onClick(View v) {
                dbHelper.updateInfo(name,password,birth,gender);
            }
        });
    }
}
