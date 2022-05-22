package com.example.gotouringv2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.Button;
import android.widget.EditText;


import com.example.gotouringv2.Entities.TripInfo;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InsertTIFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InsertTIFragment extends Fragment {
    EditText editTextINSTI1,editTextINSTI2,editTextINSTI3,editTextINSTI4,editTextINSTI5;
    Button submituser1;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InsertTIFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InsertTIFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InsertTIFragment newInstance(String param1, String param2) {
        InsertTIFragment fragment = new InsertTIFragment();
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
        View view = inflater.inflate(R.layout.fragment_insertti, container, false);
        editTextINSTI1 = view.findViewById(R.id.editTextINSTI1);
        editTextINSTI2 = view.findViewById(R.id.editTextINSTI2);
        editTextINSTI3 = view.findViewById(R.id.editTextINSTI3);
        editTextINSTI4 = view.findViewById(R.id.editTextINSTI4);
        editTextINSTI5 = view.findViewById(R.id.editTextINSTI5);

        submituser1 = view.findViewById(R.id.submituser1);
        submituser1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int Var_userid = 0;
                try {
                    Var_userid = Integer.parseInt(editTextINSTI1.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }
                String Var_TripInfo_City = editTextINSTI2.getText().toString();
                String TravelAgency_Country = editTextINSTI3.getText().toString();
                String TravelAgency_TripDuration = editTextINSTI4.getText().toString();
                String TravelAgency_TripType = editTextINSTI5.getText().toString();

                try{
                    TripInfo tripInfo = new TripInfo();
                    tripInfo.setId(Var_userid);
                    tripInfo.setCity(Var_TripInfo_City);
                    tripInfo.setCountry(TravelAgency_Country);
                    tripInfo.setTripduration(TravelAgency_TripDuration);
                    tripInfo.setTriptype(TravelAgency_TripType);

                    MainActivity.travelGuideDatabase.travelGuideDao().insertTripInfo(tripInfo);
                    Toast.makeText(getActivity(), "Ola kala", Toast.LENGTH_LONG).show();
                }catch(Exception e){
                    String message = e.getMessage();
                    Toast.makeText(getActivity(),message,Toast.LENGTH_LONG).show();
                }

                editTextINSTI1.setText("");
                editTextINSTI2.setText("");
                editTextINSTI3.setText("");
                editTextINSTI4.setText("");
                editTextINSTI5.setText("");
            }
        });
        return view;
    }

    }
