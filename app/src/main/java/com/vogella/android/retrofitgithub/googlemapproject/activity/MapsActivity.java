package com.vogella.android.retrofitgithub.googlemapproject.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.google.maps.android.ui.IconGenerator;
import com.vogella.android.retrofitgithub.googlemapproject.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ClusterManager<StringClusterItem> mClusterManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mClusterManager = new ClusterManager<>(this, mMap);
        mMap.setOnCameraChangeListener(mClusterManager);


        for (int i = 0; i < 10; i++) {
            final LatLng latLng = new LatLng(5.353383828736456 + i , -4.017343254743886 + i );
            mClusterManager.addItem(new StringClusterItem("Marker #" + (i + 1), latLng," details "));
        }

        final LatLng latLng1 = new LatLng(5.38793, -3.987550000000001);
        mClusterManager.addItem(new StringClusterItem("Angre", latLng1,", 2 plateaux 7e tranche"));

        final LatLng latLng2 = new LatLng(5.42262, -4.01564);
        mClusterManager.addItem(new StringClusterItem("Abobo", latLng2,", face au grand marche"));

        final LatLng latLng3 = new LatLng(5.353383828736456, -4.017343254743886);
        mClusterManager.addItem(new StringClusterItem("Adjame 220 Lgts", latLng3,"pres de Hassan"));

        final LatLng latLng4 = new LatLng(5.35522, -4.02696);
        mClusterManager.addItem(new StringClusterItem("Adjame", latLng4," pres de la mosquee"));

        mClusterManager.cluster();


        final CustomClusterRenderer renderer = new CustomClusterRenderer(this, mMap, mClusterManager);
        mClusterManager.setRenderer(renderer);



        mClusterManager.setOnClusterClickListener(
                new ClusterManager.OnClusterClickListener<StringClusterItem>() {
                    @Override public boolean onClusterClick(Cluster<StringClusterItem> cluster) {
                        Toast.makeText(MapsActivity.this, "Cluster click", Toast.LENGTH_SHORT).show();
                        // if true, do not move camera
                        return false;
                    }
                });
        mClusterManager.setOnClusterItemClickListener(
                new ClusterManager.OnClusterItemClickListener<StringClusterItem>() {
                    @Override public boolean onClusterItemClick(StringClusterItem clusterItem) {
                        Toast.makeText(MapsActivity.this, "Cluster item click", Toast.LENGTH_SHORT).show();
                        // if true, click handling stops here and do not show info view, do not move camera
                        // you can avoid this by calling:
                        // renderer.getMarker(clusterItem).showInfoWindow();
                        return false;
                    }
                });

        mMap.setOnMarkerClickListener(mClusterManager);



        mClusterManager.getMarkerCollection()
                .setOnInfoWindowAdapter(new CustomInfoViewAdapter(LayoutInflater.from(this)));
        mMap.setInfoWindowAdapter(mClusterManager.getMarkerManager());
    }



}
