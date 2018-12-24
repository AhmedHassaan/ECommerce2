package com.example.lenovo.e_commerce;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    FusedLocationProviderClient mFusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getApplicationContext());
        Thread thread = new Thread() {
            @Override
            public void run() {
                try { Thread.sleep(2000); }
                catch (InterruptedException e) {}
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getLocation();
                    }
                });
            }
        };
        thread.start();
    }


    private void getLocation(){

        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION},1);
        }
        else {
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations, this can be null.
                            if (location != null) {
                                // Logic to handle location object
                                Toast.makeText(getApplicationContext(), location.getLatitude() + "  " + location.getLongitude(), Toast.LENGTH_LONG).show();
                                MarkerOptions mOption = new MarkerOptions();
                                LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
                                mOption.position(ll);
                                mMap.clear();
                                mMap.addMarker(mOption);
                                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ll, 16));
                                CompleteOrder.address = location.getLatitude()+","+location.getLongitude();
                            }
                        }
                    });
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                double lat = latLng.latitude;
                double longt = latLng.longitude;
                Toast.makeText(getApplicationContext(),"You Clicked " + lat + " ---- " + longt,Toast.LENGTH_SHORT).show();
                MarkerOptions mOption = new MarkerOptions();
                LatLng ll = new LatLng(lat,longt);
                mOption.position(ll);
                mMap.clear();
                mMap.addMarker(mOption);
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ll,16));
            }
        });
    }

    public void mapDone(View view) {
        finish();
    }
}
