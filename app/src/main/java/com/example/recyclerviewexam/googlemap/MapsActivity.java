package com.example.recyclerviewexam.googlemap;

import android.Manifest;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.example.recyclerviewexam.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final int MY_PERMISSIONS_REQUEST_FINE_LOCATION = 1000;
    private GoogleMap mMap;

    private FusedLocationProviderClient mFusedLocationProviderClient;
    private LocationCallback mLocationCallback;

    PolylineOptions mRectOptions = new PolylineOptions()
            .color(Color.RED).width(5);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    Toast.makeText(MapsActivity.this, "위치 갱신 됨", Toast.LENGTH_SHORT).show();
                    // Update UI with location data
                    // ...
                    LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 13.0f));

                    // 그리기
                    mRectOptions.add(currentLocation);
                    mMap.addPolyline(mRectOptions);
                }
            }

            ;
        };
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

        // 롱클릭
        mMap.setOnMapLongClickListener(latLng -> {
            List<Address> addresses;
            Geocoder geocoder = new Geocoder(MapsActivity.this, Locale.getDefault());

            try {
                addresses = geocoder.getFromLocation(latLng.latitude,
                        latLng.longitude,
                        1);
                String address = addresses.get(0).getAddressLine(0);
                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                String postalCode = addresses.get(0).getPostalCode();
                String knownName = addresses.get(0).getFeatureName();

                mMap.addMarker(new MarkerOptions().position(latLng).title(address));

            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        TedPermission.with(this)
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("설정에서 언제든지 권한을 변경할 수 있습니다.")
                .setPermissions(Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION)
                .check();


        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13.0f));
    }


    @Override
    protected void onResume() {
        super.onResume();
        startLocationUpdates();
    }

    private void startLocationUpdates() {
        TedPermission.with(this)
                .setPermissionListener(permissionlistener2)
                .setDeniedMessage("설정에서 언제든지 권한을 변경할 수 있습니다.")
                .setPermissions(Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION)
                .check();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopLocationUpdates();
    }

    private void stopLocationUpdates() {
        mFusedLocationProviderClient.removeLocationUpdates(mLocationCallback);
    }


    @SuppressLint("MissingPermission")
    PermissionListener permissionlistener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            // 허락 됨
            mMap.setMyLocationEnabled(true);
            startLocationUpdates();
        }

        @Override
        public void onPermissionDenied(List<String> deniedPermissions) {

        }


    };

    @SuppressLint("MissingPermission")
    PermissionListener permissionlistener2 = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            // 허락됨
            LocationRequest request = new LocationRequest();
            request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            request.setInterval(10000);
            request.setFastestInterval(5000);

            mFusedLocationProviderClient.requestLocationUpdates(
                    request,
                    mLocationCallback,
                    null /* Looper */);
        }

        @Override
        public void onPermissionDenied(List<String> deniedPermissions) {

        }


    };
}
