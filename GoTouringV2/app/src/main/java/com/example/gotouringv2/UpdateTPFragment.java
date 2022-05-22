package com.example.gotouringv2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gotouringv2.Entities.TravelPackage;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UpdateTPFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpdateTPFragment extends Fragment {
    EditText upeditTextTP1,upeditTextTP2,upeditTextTP3,upeditTextTP4,upeditTextTP5;
    Button updateuser2;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UpdateTPFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UpdateTPFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UpdateTPFragment newInstance(String param1, String param2) {
        UpdateTPFragment fragment = new UpdateTPFragment();
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
        View view = inflater.inflate(R.layout.fragment_updatetp, container, false);
        upeditTextTP1 = view.findViewById(R.id.upeditTextTP1);
        upeditTextTP2 = view.findViewById(R.id.upeditTextTP2);
        upeditTextTP3 = view.findViewById(R.id.upeditTextTP3);
        upeditTextTP4 = view.findViewById(R.id.upeditTextTP4);
        upeditTextTP5 = view.findViewById(R.id.upeditTextTP5);

        updateuser2 = view.findViewById(R.id.updateuser2);
        updateuser2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int Var_userid = 0;
                try {
                    Var_userid = Integer.parseInt(upeditTextTP1.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }

                int  Agency_Id = 0;
                try {
                    Agency_Id = Integer.parseInt(upeditTextTP2.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }


                int  Trip_Id = 0;
                try {
                    Trip_Id = Integer.parseInt(upeditTextTP3.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }




                String Departure_Date = upeditTextTP4.getText().toString();

                int  Package_price = 0;
                try {
                    Package_price = (int) Double.parseDouble(upeditTextTP5.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }


                try{
                    TravelPackage travelPackage = new TravelPackage();
                    travelPackage.setId(Var_userid);
                    travelPackage.setAgencyId(Agency_Id);
                    travelPackage.setTripId(Trip_Id);
                    travelPackage.setDepartureDate(Departure_Date);
                    travelPackage.setPrice(Package_price);


                    MainActivity.travelGuideDatabase.travelGuideDao().updateTravelPackage(travelPackage);
                    Toast.makeText(getActivity(), "Ola kala", Toast.LENGTH_LONG).show();
                }catch(Exception e){
                    String message = e.getMessage();
                    Toast.makeText(getActivity(),message,Toast.LENGTH_LONG).show();
                }


                upeditTextTP1.setText("");
                upeditTextTP2.setText("");
                upeditTextTP3.setText("");
                upeditTextTP4.setText("");
                upeditTextTP5.setText("");
            }
        });
        return view;
    }
}