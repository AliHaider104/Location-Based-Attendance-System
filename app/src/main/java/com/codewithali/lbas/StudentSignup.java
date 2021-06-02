package com.codewithali.lbas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StudentSignup extends AppCompatActivity {

    EditText name;
    EditText rollNumber;
    EditText degree;
    EditText password;
    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_signup);

        name=(EditText) findViewById(R.id.Name);
        rollNumber=(EditText) findViewById(R.id.rollNumber);
        degree=(EditText) findViewById(R.id.degree);
        password=(EditText) findViewById(R.id.password);
        database= FirebaseDatabase.getInstance().getReference("Student");

    }

    public void SIGN_UP_STUDENT(View view)
    {
        String StudentName=name.getText().toString();
        String StudentRollNumber=rollNumber.getText().toString();
        String StudentDegree=degree.getText().toString();
        String StudentPassword=password.getText().toString();

        if(!TextUtils.isEmpty(StudentName) && !TextUtils.isEmpty(StudentRollNumber)
        && !TextUtils.isEmpty(StudentDegree) && !TextUtils.isEmpty(StudentPassword))
        {
            Student student=new Student(StudentName,StudentRollNumber,StudentPassword,StudentDegree);
            database.child(StudentRollNumber).setValue(student);
            Toast.makeText(this,"Account Created Successfully",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this,"Please Provide All Data",Toast.LENGTH_LONG).show();
        }

    }





}