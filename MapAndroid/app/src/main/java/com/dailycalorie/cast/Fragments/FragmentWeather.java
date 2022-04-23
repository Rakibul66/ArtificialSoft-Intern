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

import com.dailycalorie.cast.Activity.LocationChange.LocationChangeActivity;
import com.dailycalorie.cast.Activity.Trackme.TrackmeActivity;
import com.dailycalorie.cast.Activity.Weather.WeatherActivity;
import com.dailycalorie.cast.Forecast.ForecastActivity;
import com.dailycalorie.cast.R;


public class FragmentWeather extends Fragment {

    CardView weather,forecast,track,locchange;

    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_weather, container, false);

     weather=view.findViewById(R.id.weather);
     track=view.findViewById(R.id.Trackme);
     locchange=view.findViewById(R.id.locchange);


     forecast=view.findViewById(R.id.Fav);
        weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WeatherActivity.class);
                startActivity(intent);
            }
        });
        forecast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ForecastActivity.class);
                startActivity(intent);
            }
        });
        track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TrackmeActivity.class);
                startActivity(intent);
            }
        });
        locchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LocationChangeActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

}
