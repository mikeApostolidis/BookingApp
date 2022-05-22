package com.example.gotouringv2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gotouringv2.Entities.TravelAgency;
import com.example.gotouringv2.Entities.TravelPackage;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QueryTPFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QueryTPFragment extends Fragment {

    TextView txtquery3;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public QueryTPFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QueryTPFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QueryTPFragment newInstance(String param1, String param2) {
        QueryTPFragment fragment = new QueryTPFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_query, container, false);
        txtquery3 = view.findViewById(R.id.txtquery);

        List<TravelPackage> travelPackage = MainActivity.travelGuideDatabase.travelGuideDao().getTravelPackages();
        String result ="";
        String result2="";
        for (TravelPackage i: travelPackage) {
            int code = i.getId();
            int AgencyId=i.getAgencyId();
            int tripId=i.getTripId();
            String departureDate=i.getDepartureDate();
            double packagePrice=i.getPrice();
            result = result + "\nPackage Id: " +code+ "\nAgency Id: " +tripId+ "\nTrip  Id: " +AgencyId+ "\n" +
                    "Departure Date: "+departureDate+"\nPackage Price: "+packagePrice+"\n";
        }

        txtquery3.setText(result);
        return view;
    }
}