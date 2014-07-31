package com.unknottedb.bartscheduler;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class ScheduleAdapter extends ArrayAdapter<BartStation>{
    Activity context;
    List<BartStation> mStations;

    public ScheduleAdapter(Activity context, ArrayList<BartStation> stations){
        super(context, R.layout.item_station, stations);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        BartStation station = getItem(position);
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_station, parent, false);
        }
        TextView stationNameView = (TextView)convertView.findViewById(R.id.stationNameTextView);
        TextView stationAddrView = (TextView)convertView.findViewById(R.id.stationAddressTextView);
        TextView stationAbbrView = (TextView)convertView.findViewById(R.id.stationAbbrTextView);

        stationNameView.setText(station.getName());
        stationAddrView.setText(station.getAddress());
        stationAbbrView.setText(station.getAbbr());

        return convertView;
    }
}
