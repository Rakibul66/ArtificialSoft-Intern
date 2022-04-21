package com.example.googlemaptask.View.Branch;
import androidx.fragment.app.FragmentActivity;
import android.os.Bundle;
import com.example.googlemaptask.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.ArrayList;
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {


    private GoogleMap mMap;

    // below are the latitude and longitude
    // of 4 different locations.

    LatLng Lalbagh_Fort = new LatLng(23.7189, 90.3882);
    LatLng Ahsan_monjil = new LatLng(23.7085, 90.4060);
    LatLng National_parlament = new LatLng(23.7625, 90.3786);

    private ArrayList<LatLng> locationArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        locationArrayList = new ArrayList<>();
        locationArrayList.add(Lalbagh_Fort);
        locationArrayList.add(Ahsan_monjil);
        locationArrayList.add(National_parlament);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        for (int i = 0; i < locationArrayList.size(); i++) {

            // below line is use to add marker to each location of our array list.
           // mMap.addMarker(new MarkerOptions().position(locationArrayList.get(i)).title("Marker"));
            MarkerOptions mo = new MarkerOptions().position(Lalbagh_Fort).title("Lalbag").visible(true);
            MarkerOptions Ah = new MarkerOptions().position(Ahsan_monjil).title("Ahsan Monjil").visible(true);
            MarkerOptions Np = new MarkerOptions().position(National_parlament).title("National Parlament").visible(true);
            Marker marker = mMap.addMarker(mo);
            Marker marker2 = mMap.addMarker(Ah);
            Marker marker3 = mMap.addMarker(Np);

            marker.showInfoWindow();
            marker2.showInfoWindow();
            marker3.showInfoWindow();
            // below lin is use to zoom our camera on map.
            mMap.animateCamera(CameraUpdateFactory.zoomTo(58.0f));

            // below line is use to move our camera to the specific location.
            mMap.moveCamera(CameraUpdateFactory.newLatLng(locationArrayList.get(i)));
        }
    }
}