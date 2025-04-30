package com.example.ccdbookingapp;
// Christos Giapitzakis 3200034
// Dimitris Louridas 3200281
//Christos Katsaros 3210070
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class AccommodationsAdapter extends BaseAdapter {
    private ArrayList<Accommodation> accommodations;
    private final Context context;

    public AccommodationsAdapter(Context context, ArrayList<Accommodation> accommodations) {
        this.accommodations = accommodations;
        this.context = context;
    }
    @Override
    public int getCount() {
        return accommodations.size();
    }

    @Override
    public Object getItem(int i) {
        return accommodations.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        HolderView holderView;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.main_fragment, viewGroup, false);
            holderView = new HolderView(view);
            view.setTag(holderView);
        } else {
            holderView = (HolderView) view.getTag();
        }

        Accommodation a = accommodations.get(i);
        holderView.roomName.setText(a.getRoomName());
        holderView.areaName.setText(a.getArea());
        holderView.ratingBar.setRating((float)a.getStars());

        return view;
    }

    private static class HolderView {
        private final TextView roomName, areaName;
        private final RatingBar ratingBar;

        public HolderView(View view) {
            roomName = view.findViewById(R.id.textView);
            areaName = view.findViewById(R.id.textView2);
            ratingBar = view.findViewById(R.id.ratingBar2);
        }
    }
}
