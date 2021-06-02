package com.codewithali.lbas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TeacherFunctions extends AppCompatActivity {

    private static final int REQUEST_CODE_LOCATION_PERMISSION = 1;
    TextView textView;

    DatabaseReference db;
    EditText cnic;
    EditText course_name;
    EditText date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_functions);
        textView= findViewById(R.id.longlat);

        cnic=(EditText) findViewById(R.id.TeacherCnic);
        course_name=(EditText) findViewById(R.id.CourseName);
        date=(EditText) findViewById(R.id.date);
        db= FirebaseDatabase.getInstance().getReference("Class");


        findViewById(R.id.startclass).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(
                        getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                            TeacherFunctions.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            REQUEST_CODE_LOCATION_PERMISSION
                    );

                } else {
                    getCurrentLocation();
                }
            }

        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE_LOCATION_PERMISSION && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation();
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getCurrentLocation() {

        final String cn= course_name.getText().toString();
        final String Cnic=cnic.getText().toString();
        final String Date=date.getText().toString();



        final LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationServices.getFusedLocationProviderClient(TeacherFunctions.this)
                .requestLocationUpdates(locationRequest, new LocationCallback() {

                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        super.onLocationResult(locationResult);
                        LocationServices.getFusedLocationProviderClient(TeacherFunctions.this)
                                .removeLocationUpdates(this);

                        if(locationResult!=null && locationResult.getLocations().size()>0)
                        {
                            int latestLocationIndex= locationResult.getLocations().size()-1;
                            double latitude=
                                    locationResult.getLocations().get(latestLocationIndex).getLatitude();
                            double longitude=
                                    locationResult.getLocations().get(latestLocationIndex).getLongitude();


                            if(!TextUtils.isEmpty(cn) && !TextUtils.isEmpty(Date) && !TextUtils.isEmpty(Cnic))
                            {
                                ClassData CD=new ClassData(Date,Cnic,cn,latitude,longitude);
                                db.child(Date).setValue(CD);
                            }


                          textView.setText(
                                  String.format("Shared Location : Longitude: "+ longitude+" Latitude: "+latitude)
                          );



                        }
                    }
                }, Looper.getMainLooper());
    }
}