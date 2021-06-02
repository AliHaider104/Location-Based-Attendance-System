package com.codewithali.lbas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StudentLogin extends AppCompatActivity {

    EditText rollNumber;
    EditText Password;
    DatabaseReference database;
    TextView Show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        rollNumber=(EditText) findViewById(R.id.TeacherCnic);
        Password=(EditText) findViewById(R.id.teacherPassword);
        Show=(TextView) findViewById(R.id.show);


    }

    public void STUDENT_SIGN(View view)
    {
        Intent intent=new Intent(this, StudentSignup.class);
        startActivity(intent);
    }

    public void STUDENT_LOGIN(View view)
    {
        String roll=rollNumber.getText().toString();
        final String pass=Password.getText().toString();

        if(!TextUtils.isEmpty(roll) && !TextUtils.isEmpty(pass)) {

            database = FirebaseDatabase.getInstance().getReference("Student").child(rollNumber.getText().toString());

            database.addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    if(dataSnapshot.getValue()!=null) {

                        String PASSWORD = dataSnapshot.child("password").getValue().toString();

                        if (PASSWORD.contentEquals(pass))
                            Show.setText("LOGIN");
                        else
                            Show.setText("INCORRECT NAME OR PASSWORD");
                    }
                    else
                        Show.setText("NO RECORD FOUND");
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
        }
        else
            Toast.makeText(this, "Please Provide All Data",Toast.LENGTH_LONG).show();
    }

}