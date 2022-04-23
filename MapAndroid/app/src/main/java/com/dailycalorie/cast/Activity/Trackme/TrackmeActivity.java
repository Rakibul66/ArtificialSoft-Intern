package com.dailycalorie.cast.Activity.Trackme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dailycalorie.cast.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TrackmeActivity extends AppCompatActivity {
    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();



    Button btnGetLocation, btnShowPosition;
    TextView textLocation;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final int REQUEST_CODE_PERMISSION=2;

    GPSTracker gps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trackme);
        try {
            if (ActivityCompat.checkSelfPermission(this, mPermission)
                    != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this, new String[]{mPermission},
                        REQUEST_CODE_PERMISSION);


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        btnGetLocation=(Button)findViewById(R.id.btnGetLocation);
        btnGetLocation.setOnClickListener(new View.OnClickListener(){
            // Write a message to the database


            @Override
            public void onClick(View v) {
                gps= new GPSTracker(TrackmeActivity.this);
                textLocation=findViewById(R.id.textGetLocation);
                if(gps.canGetLocation()){
                    double latitude=gps.getLatitude();
                    double longitude=gps.getLongitude();
                    textLocation.setText(latitude+ " "+ longitude);
                    myRef =database.getReference("Location");
                    myRef.setValue(latitude+" "+longitude);
                }else{
                    gps.showSettingsAlert();
                }
            }
        });


    }
}
