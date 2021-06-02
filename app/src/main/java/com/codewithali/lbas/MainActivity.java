package com.codewithali.lbas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void STUDENT_LOGIN(View view) {

        Intent intent=new Intent(this, StudentLogin.class);
        startActivity(intent);


    }

    public void TEACHER_LOGIN(View view){
        Intent intent=new Intent(this, TeacherLogin.class);
        startActivity(intent);
    }


}