package com.example.gotouringv2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gotouringv2.Entities.TravelAgency;
import com.example.gotouringv2.Entities.TripInfo;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QueryTIFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QueryTIFragment extends Fragment {
 TextView txtquery2;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public QueryTIFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QueryTIFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QueryTIFragment newInstance(String param1, String param2) {
        QueryTIFragment fragment = new QueryTIFragment();
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
        txtquery2 = view.findViewById(R.id.txtquery);

        List<TripInfo> tripInfo = MainActivity.travelGuideDatabase.travelGuideDao().getTripInfos();
        String result ="";

        for (TripInfo i: tripInfo) {
            int code = i.getId();
            String city = i.getCity();
            String country = i.getCountry();
            String duration =i.getTripduration();
            String type = i.getTriptype();
            result = result + "\n Id: " + code + "\n city: " + city + "\n country: " + country + "\n Duration: " + duration +"\nType: "+type+"\n" ;
        }

        txtquery2.setText(result);
        return view;
    }
}