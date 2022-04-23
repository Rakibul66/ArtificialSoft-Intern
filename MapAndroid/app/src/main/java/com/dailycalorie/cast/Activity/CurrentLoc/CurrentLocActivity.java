package com.dailycalorie.cast.Activity.CurrentLoc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dailycalorie.cast.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class CurrentLocActivity extends AppCompatActivity {
    //Declare The Variables
    Button buttonGetLocation,map;
    TextView textView1, textView2, textView3, textView4, textView5,textView6;
    FusedLocationProviderClient fusedLocationProviderClient;
    Calendar calander;
    SimpleDateFormat simpleDateFormat;
    String Date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_loc);
        buttonGetLocation = findViewById(R.id.buttonGetLocation);
        textView1 = findViewById(R.id.text1);
        textView2 = findViewById(R.id.text2);
        textView3 = findViewById(R.id.text3);
        textView4 = findViewById(R.id.text4);
        textView5 = findViewById(R.id.text5);
        map = findViewById(R.id.map);

        //go to map activity
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),MapActivity.class);
                startActivity(intent);
            }
        });

        //Initilize the Variable
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        buttonGetLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    //When Permission Granted
                    getLocation();
                } else {
                    ActivityCompat.requestPermissions(CurrentLocActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                }

            }
        });
    }

    private void getLocation() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                                                                                @Override
                                                                                public void onComplete(@NonNull Task<Location> task) {
                                                                                    //Initialize Location
                                                                                    Location location = task.getResult();
                                                                                    if (location != null) {

                                                                                        try {
                                                                                            //initialize Geo Coder
                                                                                            Geocoder geocoder = new Geocoder(CurrentLocActivity.this, Locale.getDefault());
                                                                                            //initialize Address List
                                                                                            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                                                                                            //Set Location to TextViews
                                                                                            textView1.setText(Html.fromHtml(
                                                                                                    "<font color=''#6200EE'><b>Latitude :</b><br></font>" + addresses.get(0).getLatitude()
                                                                                            ));
                                                                                            textView2.setText(Html.fromHtml(
                                                                                                    "<font color=''#6200EE'><b>Longitude :</b><br></font>" + addresses.get(0).getLongitude()
                                                                                            ));
                                                                                            textView3.setText(Html.fromHtml(
                                                                                                    "<font color=''#6200EE'><b>Country :</b><br></font>" + addresses.get(0).getCountryName()
                                                                                            ));
                                                                                            textView4.setText(Html.fromHtml(
                                                                                                    "<font color=''#6200EE'><b>Locality :</b><br></font>" + addresses.get(0).getLocality()
                                                                                            ));
                                                                                            textView5.setText(Html.fromHtml(
                                                                                                    "<font color=''#6200EE'><b>Address :</b><br></font>" + addresses.get(0).getAddressLine(0)
                                                                                            ));
                                                                                        } catch (IOException e) {
                                                                                            e.printStackTrace();
                                                                                        }

                                                                                    }
                                                                                }
                                                                            }
        );
    }
}
