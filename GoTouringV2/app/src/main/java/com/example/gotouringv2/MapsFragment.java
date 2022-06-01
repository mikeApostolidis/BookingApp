package com.example.gotouringv2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsFragment extends Fragment {
    String data;
    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
//            Bundle bundle=MapsFragment.super.getArguments();
//            String city=bundle.getString("key");
//            Toast.makeText(getActivity(),city,Toast.LENGTH_LONG).show();
//            Bundle bundle=MapsFragment.super.getArguments();
//            Toast.makeText(getActivity(),bundle.getString("cityToFind"),Toast.LENGTH_LONG).show();
            String city="Thessaloniki";
            Geocoder gc= new Geocoder(getActivity(), Locale.getDefault());
            List<Address> adList;
            try {
                adList=gc.getFromLocationName(city,2);
                Address first=adList.get(0);
                LatLng cityToFind = new LatLng(first.getLatitude(), first.getLongitude());
                googleMap.addMarker(new MarkerOptions().position(cityToFind).title("Marker in Sydney"));
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(cityToFind));
                //Address second=adList.get(1);
            } catch (IOException e) {
                e.printStackTrace();
            }



        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_maps, container, false);
        Bundle bundle=this.getArguments();
        Toast.makeText(getActivity(),bundle.getString("cityToFind"),Toast.LENGTH_LONG).show();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}