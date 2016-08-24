package com.vogella.android.retrofitgithub.googlemapproject.activity;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

/**
 * Created by sfl on 22/08/2016.
 */
public class StringClusterItem implements ClusterItem {
    final String title;
    final LatLng latLng;
    public String details;

    public StringClusterItem(String title, LatLng latLng, String details) {
        this.title = title;
        this.latLng = latLng;
        this.details = details;
    }
    @Override public LatLng getPosition() {
        return latLng;
    }
}