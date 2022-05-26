package com.example.gotouringv2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.view.View.OnClickListener;

import com.example.gotouringv2.Entities.TravelAgency;
import com.example.gotouringv2.Entities.TravelPackage;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QueryTPFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QueryTPFragment extends Fragment {

    Spinner spinner;
    TextView txtquery3;
   ArrayAdapter<CharSequence> adapter;
    Button buttonQuery;
    int temp;

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
        View view = inflater.inflate(R.layout.fragment_querytp, container, false);
        txtquery3 = view.findViewById(R.id.txtquery3);
        spinner = view.findViewById(R.id.spinner);
        buttonQuery = view.findViewById(R.id.buttonQuery);
        adapter = ArrayAdapter.createFromResource(getContext(), R.array.querries_array, R.layout.support_simple_spinner_dropdown_item );//, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id){

               temp=position+1;
           }
               @Override
               public void onNothingSelected(AdapterView<?> parent) {}
        });

        buttonQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //txtquery3.setText("temp"+temp);
                String result = "";
//Den leitourgei to case 2 3 4
                switch (temp){
                    case 1:
                        List<TravelPackage> travelPackage1 = MainActivity.travelGuideDatabase.travelGuideDao().getTravelPackages();
                        for (TravelPackage i : travelPackage1) {
                            int code = i.getId();
                            int AgencyId = i.getAgencyId();
                            int tripId = i.getTripId();
                            String departureDate = i.getDepartureDate();
                            double packagePrice = i.getPrice();
                            result = result + "\nPackage Id: " + code + "\nAgency Id: " + tripId + "\nTrip  Id: " + AgencyId + "\n" +
                                    "Departure Date: " + departureDate + "\nPackage Price: " + packagePrice + "\n";
                        }
                        txtquery3.setText(result);
                        break;

                    case 2:
                        //List<TravelPackage> travelPackage = MainActivity.travelGuideDatabase.travelGuideDao().getTravelPackages();
                        List<Integer> integers = MainActivity.travelGuideDatabase.travelGuideDao().getQueryMostAgencies();
                        for(Integer i: integers)
                        {
                            result = result + "\n Most famous Agency "+ i +"\n";
                        }
                        txtquery3.setText(result);
                        break;
                    case 3:
                        //List<TravelPackage> travelPackage = MainActivity.travelGuideDatabase.travelGuideDao().getTravelPackages();
                        List<Integer> integers1 = MainActivity.travelGuideDatabase.travelGuideDao().getQueryMostPickedTrip();
                        for(Integer i: integers1)
                        {
                            result = result + "\n Most Trip "+ i +"\n";
                        }
                        txtquery3.setText(result);
                        break;
                    case 4:
                        //List<TravelPackage> travelPackage = MainActivity.travelGuideDatabase.travelGuideDao().getTravelPackages();
                        List<Double> doubles = MainActivity.travelGuideDatabase.travelGuideDao().getQueryCheapestTrip();
                        for(Double i: doubles)
                        {
                            result = result + "\n Least expensive Trip "+ i +"\n";
                        }
                        txtquery3.setText(result);
                        break;

                }
            }
        });
        return view;
    }
}