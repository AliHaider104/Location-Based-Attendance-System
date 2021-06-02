package com.codewithali.lbas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TeacherSignup extends AppCompatActivity {

    EditText name;
    EditText cnic;
    EditText password;
    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_signup);

        name=(EditText) findViewById(R.id.Name);
        cnic=(EditText) findViewById(R.id.cnic);
        password=(EditText) findViewById(R.id.password);

        database= FirebaseDatabase.getInstance().getReference("Teacher");

    }

    public void SIGN_UP_TEACHER(View view)
    {
        String n=name.getText().toString();
        String c=cnic.getText().toString();
        String p=password.getText().toString();

        if(!TextUtils.isEmpty(n) && !TextUtils.isEmpty(c) && !TextUtils.isEmpty(p))
        {
            Teacher teacher=new Teacher(n,c,p);
            database.child(c).setValue(teacher);
            Toast.makeText(this,"Account Created Successfully",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this,"Please Provide All Data",Toast.LENGTH_LONG).show();
        }


    }

}