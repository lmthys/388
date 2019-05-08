package com.example.lmandrew.google_maps_activity;

import android.graphics.Point;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.SphericalUtil;

import java.util.Map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private LatLng point1;
    private LatLng point2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        point1 = null;
        point2 = null;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int)event.getX();
        int y = (int)event.getY();
        Point p = new Point(x,y);
        if(point1 == null) {
            point1 = mMap.getProjection().fromScreenLocation(p);
        }
        else {
            point2 = mMap.getProjection().fromScreenLocation(p);
            distance(point1,point2);
        }
        return super.onTouchEvent(event);
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
        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.mapstyle_mine));
        // Add a marker in Sydney and move the camera
        LatLng home = new LatLng(42.040062, -93.641027);
        mMap.addMarker(new MarkerOptions().position(home).title("Larisa's Home"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(home));
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if(point1 == null){
                    point1 = latLng;
                    Marker m = mMap.addMarker(new MarkerOptions().position(latLng));
                    m.setTitle("Point 1");
                }else {
                    point2 = latLng;
                    distance(point1,point2);
                    Marker m = mMap.addMarker(new MarkerOptions().position(latLng));
                    m.setTitle("Point 2");
                }

            }
        });
        mMap.setOnMarkerClickListener(this);
    }

    public double distance(LatLng point1, LatLng point2){
        if(point1 == null || point2 == null) {
            return 0;
        }
        double distance = Math.round(SphericalUtil.computeDistanceBetween(point1, point2));
        Toast.makeText(getApplicationContext(), "The distance is "+distance, Toast.LENGTH_LONG).show();
        return distance;
    }
    @Override
    public boolean onMarkerClick(final Marker marker){
        Toast.makeText(getApplicationContext(), "The marker's position is "+marker.getPosition(), Toast.LENGTH_LONG).show();
        marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
        return false;

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
