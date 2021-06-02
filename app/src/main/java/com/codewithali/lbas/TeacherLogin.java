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

public class TeacherLogin extends AppCompatActivity {

    EditText cnic;
    EditText pass;
    TextView show;
    DatabaseReference db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);

        cnic=(EditText) findViewById(R.id.TeacherCnic);
        pass=(EditText) findViewById(R.id.teacherPassword);
        show=(TextView) findViewById(R.id.show);

    }

    public void TEACHER_SIGNUP(View view)
    {
        Intent intent=new Intent(this, TeacherSignup.class);
        startActivity(intent);
    }

    public void TEACHER_LOGIN(View view)
    {

        String c=cnic.getText().toString();
        final String p=pass.getText().toString();

        if(!TextUtils.isEmpty(c) && !TextUtils.isEmpty(p))
        {
            final Intent intent=new Intent(this,TeacherFunctions.class);
            db= FirebaseDatabase.getInstance().getReference("Teacher").child(c);

            db.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                    if(dataSnapshot.getValue()!=null) {

                        String PASSWORD = dataSnapshot.child("password").getValue().toString();

                        if (PASSWORD.contentEquals(p)) {

                            startActivity(intent);

                            show.setText("LOGIN");

                        }
                        else
                            show.setText("INCORRECT NAME OR PASSWORD");
                    }
                    else
                        show.setText("NO RECORD FOUND");

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
        else
        {
            Toast.makeText(this,"Please Provide All Data",Toast.LENGTH_LONG).show();
        }

    }


}