package com.dailycalorie.cast.Activity.RealtimeLoc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.dailycalorie.cast.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RealtimeLocActivity extends AppCompatActivity {
    String timer[] = {"Select time", "5 sec", "10 sec", "15 sec", "20 sec", "30 sec"};
    String tim;
    Button mLocationBtn;
    TextView mText;
    GPS_Service gps;

    //Firebase Work
    DatabaseReference mDatabaseLocationDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realtime_loc);
        mText = findViewById(R.id.location_tv);
        Spinner mSpinTime = findViewById(R.id.spinner_time);
        mLocationBtn = findViewById(R.id.location_btn);
        mDatabaseLocationDetails = FirebaseDatabase.getInstance().getReference("test/percel/t01/currentLocation/").child("2");

//      permission check
        if (!runtime_permission())
            enable_button();
        runtime_permission();


        mSpinTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                tim = adapterView.getItemAtPosition(i).toString();
                if (tim.equals("Select time")) {
                    Toast.makeText(getApplicationContext(), "Please Select time!", Toast.LENGTH_SHORT).show();
                }
                if (tim == "5 sec") {
                    tim = String.valueOf(tim.charAt(0));
                    Toast.makeText(getApplicationContext(), tim + " is Selected Time", Toast.LENGTH_SHORT).show();
                }
                if (tim == "10 sec") {
                    tim = tim.substring(0, 2);
                    Toast.makeText(getApplicationContext(), tim + "Selected Time", Toast.LENGTH_SHORT).show();
                }
                if (tim == "15 sec") {
                    tim = tim.substring(0, 2);
                    Toast.makeText(getApplicationContext(), tim + "Selected Time", Toast.LENGTH_SHORT).show();
                }
                if (tim == "20 sec") {
                    tim = tim.substring(0, 2);
                    Toast.makeText(getApplicationContext(), tim + "Selected Time", Toast.LENGTH_SHORT).show();
                }
                if (tim == "30 sec") {
                    tim = tim.substring(0, 2);
                    Toast.makeText(getApplicationContext(), tim + "Selected Time", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                tim = String.valueOf(0);
            }
        });

        ArrayAdapter arrayAdapterCity = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, timer);
        arrayAdapterCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinTime.setAdapter(arrayAdapterCity);
    }

    private void enable_button() {

        mLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                gps = new GPS_Service(getApplicationContext(), tim);
                startService(new Intent(getApplicationContext(), GPS_Service.class));

                if (gps.canGetLocation()) {
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();
                    storeInDatabase(latitude, longitude);
                    mText.setText("Latitude Detect:\n" + latitude +"\n" +"Longitude Detect:" + longitude);
                    Toast.makeText(getApplicationContext(), "Latitude Detect:" + latitude + "\n" + "Longitude Detect:\n" + longitude, Toast.LENGTH_SHORT).show();
                } else {
                    gps.showSettingsAlert();
                }
            }
        });


    }

    private void storeInDatabase(double latitude, double longitude) {


        mDatabaseLocationDetails.child("Longitude").setValue(longitude);
        mDatabaseLocationDetails.child("Latitude").setValue(latitude);
    }

    private boolean runtime_permission() {
        if (Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 123);
            return true;
        }
        return false;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 123) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                enable_button();
            } else {
                runtime_permission();
            }
        }
    }
}