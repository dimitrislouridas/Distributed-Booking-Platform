package com.example.ccdbookingapp;
// Christos Giapitzakis 3200034
// Dimitris Louridas 3200281
//Christos Katsaros 3210070
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.fragment.app.ListFragment;

public class AccommodationFragment extends ListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
// Inflate the layout for this fragment
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    public void setRoomName(String name) {
        TextView roomName = (TextView) getView().findViewById(R.id.textView);
        roomName.setText(name);
    }

    public void setArea(String area) {
        TextView areaView = (TextView) getView().findViewById(R.id.textView2);
        areaView.setText(area);
    }

    public void setRating(double stars) {
        RatingBar ratingBar = (RatingBar) getView().findViewById(R.id.ratingBar2);
        ratingBar.setRating((float)stars);
    }
}
