package com.dailycalorie.cast.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import com.dailycalorie.cast.Activity.CurrentLoc.MapActivity;
import com.dailycalorie.cast.Activity.DesignMap.MapsRawActivity;
import com.dailycalorie.cast.Activity.Polygon.PolyMapsActivity;
import com.dailycalorie.cast.Activity.RealtimeLoc.RealtimeLocActivity;
import com.dailycalorie.cast.R;



public class FragmentHome extends Fragment {

    View view;
    CardView one, two, three, four;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        one = view.findViewById(R.id.Polygon); //cal or define id from layout
        two = view.findViewById(R.id.loc);
        three= view.findViewById(R.id.Fav);
        four= view.findViewById(R.id.realtime);


        onClickListeners();

        return view;

    }

    //call setonclick listener for going compass,currentloc,favdestination activity
    private void onClickListeners(){
        one.setOnClickListener(view -> {
            Intent intent=new Intent(getContext(), PolyMapsActivity.class);
            startActivity(intent);
        });
        two.setOnClickListener(view -> {
            Intent intent=new Intent(getContext(), MapActivity.class);
            startActivity(intent);
        });
        three.setOnClickListener(view -> {
            Intent intent=new Intent(getContext(), MapsRawActivity.class);
            startActivity(intent);
        });
        four.setOnClickListener(view -> {
            Intent intent=new Intent(getContext(), RealtimeLocActivity.class);
            startActivity(intent);
        });
    }
}
