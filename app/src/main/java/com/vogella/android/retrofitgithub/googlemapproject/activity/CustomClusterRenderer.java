package com.vogella.android.retrofitgithub.googlemapproject.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.content.ContextCompat;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.google.maps.android.ui.IconGenerator;
import com.vogella.android.retrofitgithub.googlemapproject.R;

/**
 * Created by sfl on 22/08/2016.
 */
public class CustomClusterRenderer extends DefaultClusterRenderer<StringClusterItem> {
    private final Context mContext;
    private final IconGenerator mClusterIconGenerator;


    public CustomClusterRenderer(Context context, GoogleMap map,
                                 ClusterManager<StringClusterItem> clusterManager) {
        super(context, map, clusterManager);
        mContext = context;
        mClusterIconGenerator = new IconGenerator(mContext.getApplicationContext());
    }

    @Override
    protected void onBeforeClusterRendered(Cluster<StringClusterItem> cluster, MarkerOptions markerOptions) {
        super.onBeforeClusterRendered(cluster, markerOptions);

        mClusterIconGenerator.setBackground( ContextCompat.getDrawable(mContext, R.drawable.background_circle));
        mClusterIconGenerator.setTextAppearance(R.style.AppTheme_WhiteTextAppearance);
    //    final Bitmap icon = mClusterIconGenerator.makeIcon(String.valueOf(cluster.getSize()));
       // markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon));
    }



    @Override protected void onBeforeClusterItemRendered(StringClusterItem item,
                                                         MarkerOptions markerOptions) {

        final BitmapDescriptor markerDescriptor = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE);
        markerOptions.title(item.title).snippet(item.details);

    }
}
