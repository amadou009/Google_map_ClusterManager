package com.vogella.android.retrofitgithub.googlemapproject.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.vogella.android.retrofitgithub.googlemapproject.R;

/**
 * Created by sfl on 22/08/2016.
 */
public class CustomInfoViewAdapter implements GoogleMap.InfoWindowAdapter {
    private final LayoutInflater mInflater;
    public CustomInfoViewAdapter(LayoutInflater inflater) {
        this.mInflater = inflater;
    }
    @Override public View getInfoWindow(Marker marker) {
        final View popup = mInflater.inflate(R.layout.info_window_layout, null);
        ((TextView) popup.findViewById(R.id.title)).setText(marker.getTitle());
        ((TextView) popup.findViewById(R.id.details)).setText(marker.getSnippet());
        return popup;
        //return null;
    }

    @Override public View getInfoContents(Marker marker) {
        final View popup = mInflater.inflate(R.layout.info_window_layout, null);
        ((TextView) popup.findViewById(R.id.title)).setText(marker.getSnippet());
        return popup;
       // return  null;
    }
}
