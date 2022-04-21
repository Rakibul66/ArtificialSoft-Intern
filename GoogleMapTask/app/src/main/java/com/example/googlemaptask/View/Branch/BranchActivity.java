package com.example.googlemaptask.View.Branch;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.googlemaptask.R;
import com.example.googlemaptask.View.Branch.Model.ObjectSerializer;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.ArrayList;

public class BranchActivity extends AppCompatActivity {
    ListView listView;
    AppCompatButton addPlacesButton;
    static ArrayList<String> places;
    static ArrayList<LatLng> locations;
    static ArrayAdapter placesArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch);
        addPlacesButton=(AppCompatButton) findViewById(R.id.addPlacesButton);
        listView=(ListView) findViewById(R.id.placesView);

        places=new ArrayList<>();
        locations=new ArrayList<>();

        //get stored values from shared preferences
        SharedPreferences sp=this.getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);

        ArrayList<String> latitudes=new ArrayList<>();
        ArrayList<String> longitudes=new ArrayList<>();

        places.clear();
        latitudes.clear();
        longitudes.clear();
        locations.clear();

        try {
            places=(ArrayList<String>) ObjectSerializer.deserialize(sp.getString("places",ObjectSerializer.serialize(new ArrayList<String>())));
            latitudes=(ArrayList<String>) ObjectSerializer.deserialize(sp.getString("latitudes",ObjectSerializer.serialize(new ArrayList<String>())));
            longitudes=(ArrayList<String>) ObjectSerializer.deserialize(sp.getString("longitudes",ObjectSerializer.serialize(new ArrayList<String>())));

        } catch (IOException e) {
            e.printStackTrace();
        }

        if(places.size()>0 && latitudes.size()>0 && longitudes.size()>0)
        {
            if(places.size()==latitudes.size() && latitudes.size()==longitudes.size())
            {
                for(int i=0;i<latitudes.size();i++)
                {
                    locations.add(new LatLng(Double.parseDouble(latitudes.get(i)),Double.parseDouble(longitudes.get(i))));
                }
            }
        }

        placesArrayAdapter=new ArrayAdapter(this,R.layout.listview_text,places);
        listView.setAdapter(placesArrayAdapter);

        addPlacesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),BranchDetailsActivity.class);
                intent.putExtra("placesInfo",-1);
                startActivity(intent);

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getApplicationContext(),BranchDetailsActivity.class);
                intent.putExtra("placesInfo",i);
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                final int del=i;

                new AlertDialog.Builder(BranchActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Are you sure")
                        .setMessage("Do you want to delete this place?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                places.remove(del);
                                locations.remove(del);
                                placesArrayAdapter.notifyDataSetChanged();

                                SharedPreferences sp=getApplicationContext().getSharedPreferences(getPackageName(),Context.MODE_PRIVATE);
                                SharedPreferences.Editor ed=sp.edit();

                                ArrayList<String> lat=new ArrayList<>();
                                ArrayList<String> lon=new ArrayList<>();

                                for(LatLng cor:locations)
                                {
                                    lat.add(Double.toString(cor.latitude));
                                    lon.add(Double.toString(cor.longitude));
                                }

                                try {
                                    ed.putString("places",ObjectSerializer.serialize(places));
                                    ed.putString("latitudes",ObjectSerializer.serialize(lat));
                                    ed.putString("longitudes",ObjectSerializer.serialize(lon));
                                    ed.commit();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }


                            }
                        })
                        .setNegativeButton("No",null)
                        .show();

                return true;
            }
        });

    }
}