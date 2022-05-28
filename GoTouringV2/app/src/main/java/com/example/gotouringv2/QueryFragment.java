package com.example.gotouringv2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.gotouringv2.Entities.ResultIntInt;
import com.example.gotouringv2.Entities.ResultStringInt;
import com.example.gotouringv2.Entities.TravelAgency;
import com.example.gotouringv2.Entities.TravelPackage;
import com.example.gotouringv2.Entities.TripInfo;


import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QueryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QueryFragment extends Fragment {

    TextView txtquery;

    ArrayAdapter<CharSequence> adapter;
    Button buttonQuery;
    int temp;
    Spinner spinner;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public QueryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QueryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QueryFragment newInstance(String param1, String param2) {
        QueryFragment fragment = new QueryFragment();
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
        txtquery = view.findViewById(R.id.txtquery3);

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
                String result = "";


                switch(temp){
                    //case 1-3 show all
                    case 1:
                        List<TravelAgency> travelagency = MainActivity.travelGuideDatabase.travelGuideDao().getTravelAgencies();
                        for (TravelAgency i : travelagency) {
                            int code = i.getId();
                            String name = i.getName();
                            String surname = i.getAddress();
                            result = result + "\n Id: " + code + "\n Name: " + name + "\n Address: " + surname + "\n";
                        }
                        txtquery.setText(result);
                        break;
                    case 2:
                        List<TripInfo> tripInfos = MainActivity.travelGuideDatabase.travelGuideDao().getTripInfos();
                        for (TripInfo i: tripInfos) {
                            int code = i.getId();
                            String city = i.getCity();
                            String country = i.getCountry();
                            String duration =i.getTripduration();
                            String type = i.getTriptype();
                            result = result + "\n Id: " + code + "\n city: " + city + "\n country: " + country + "\n Duration: " + duration +"\nType: "+type+"\n" ;
                        }
                        txtquery.setText(result);
                        break;
                    case 3:
                        List<TravelPackage> travelPackage1 = MainActivity.travelGuideDatabase.travelGuideDao().getTravelPackages();
                        for (TravelPackage i : travelPackage1) {
                            int code = i.getId();
                            int AgencyId = i.getAgencyId();
                            int tripId = i.getTripId();
                            String departureDate = i.getDepartureDate();
                            double packagePrice = i.getPrice();
                            result = result + "\nPackage Id: " + code + "\nAgency Id: " + AgencyId + "\nTrip  Id: " + tripId + "\n" +
                                    "Departure Date: " + departureDate + "\nPackage Price: " + packagePrice + "\n";
                        }
                        txtquery.setText(result);
                        break;
                   //case 4-6 queries about travel agency
                    case 4:
                        List<String> strings = MainActivity.travelGuideDatabase.travelGuideDao().getAgentTraveledOnlyByPlane();
                        for (String i: strings) {
                            result = result + "\n Agencies that have travelled only with plane and not with bus : " + i + "\n";
                        }
                        txtquery.setText(result);
                        break;


                    case 5:
                        List<ResultIntInt> resultIntInts = MainActivity.travelGuideDatabase.travelGuideDao().getidAndPriceOnlyBus();
                        for (ResultIntInt i : resultIntInts) {
                            int field1 = i.getField1();
                            int field2 = i.getField2();
                            result = result + "\n Id: " +field1+ ", and price of packets that take bus : " +field2+ "€\n";
                        }
                        txtquery.setText(result);
                        break;

                    case 6:
                        List<ResultStringInt> resultStringInts4 = MainActivity.travelGuideDatabase.travelGuideDao().getQueryMostPickedCity();
                        for(ResultStringInt i: resultStringInts4)
                        {
                            String name = i.getField1();
                            int code = i.getField2();

                            result = result + "\n Name of city : "+ name +", Id of Trip "+code+" \n";
                        }
                        txtquery.setText(result);
                        break;




                        //queries 7-9 about trip info
                    case 7:
                        List<Double> doubles = MainActivity.travelGuideDatabase.travelGuideDao().getQueryCheapestTrip();
                        for (Double i : doubles) {
                            result = result + "\n Cheapest trip : " +i+ "€ \n";
                        }
                        txtquery.setText(result);
                        break;


                    case 8:
                        List<ResultStringInt> resultstringints = MainActivity.travelGuideDatabase.travelGuideDao().getDate();
                        for (ResultStringInt i : resultstringints) {
                            String name = i.getField1();
                            int code = i.getField2();
                            result = result + "\n Agency's name: " + name + "\n, Agency's code: " + code + "\n";
                        }
                        txtquery.setText(result);
                        break;
                    case 9:
                        List<Double> doubles2 = MainActivity.travelGuideDatabase.travelGuideDao().getPrice();
                        for (Double i : doubles2) {
                            result = result + "\n Most expensive price : " +i+ "€ \n";
                        }
                        txtquery.setText(result);
                        break;

                    //queries 10-12 about travel package

                    case 10:
                        List<ResultStringInt> resultStringInts1 = MainActivity.travelGuideDatabase.travelGuideDao().getidFromNames();
                        for (ResultStringInt i : resultStringInts1) {
                            int code = i.getField2();
                            String name = i.getField1();
                            result = result + "\n Agency's name that starts with J : " + name + "\n, Id: " + code + "\n";
                        }
                        txtquery.setText(result);
                        break;
                    case 11:
                        List<ResultStringInt> resultStringInts3 = MainActivity.travelGuideDatabase.travelGuideDao().getxwra();
                        for(ResultStringInt i: resultStringInts3)
                        {
                            String name = i.getField1();
                            int count = i.getField2();
                            result = result + "\n Name of country : "+ name + ", number of times :" + count +"\n";
                        }
                        txtquery.setText(result);
                        break;

                    case 12:

                        List<Double> doubles3 = MainActivity.travelGuideDatabase.travelGuideDao().getAVGPackagePrice();
                        for(Double i: doubles3)
                        {
                            result = result + "\n Average of prices "+ i +"€ \n";
                        }
                        txtquery.setText(result);
                        break;

                }

            }
        });
        return view;
    }
}