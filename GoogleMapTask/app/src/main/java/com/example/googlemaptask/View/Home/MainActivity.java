package com.example.googlemaptask.View.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.googlemaptask.R;
import com.example.googlemaptask.View.Branch.BranchActivity;
import com.example.googlemaptask.View.Branch.MapsActivity;

public class MainActivity extends AppCompatActivity {
  Button button,branch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.route);
        branch=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(intent);
            }
        });
        branch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), BranchActivity.class);
                startActivity(intent);
            }
        });
    }
}