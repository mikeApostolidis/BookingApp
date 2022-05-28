package com.example.gotouringv2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import com.example.gotouringv2.Entities.TripInfo;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UpdateTIFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpdateTIFragment extends Fragment {

    EditText upeditTextTI1,upeditTextTI2,upeditTextTI3,upeditTextTI4,upeditTextTI5;
    Button updateuser1;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UpdateTIFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UpdateTIFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UpdateTIFragment newInstance(String param1, String param2) {
        UpdateTIFragment fragment = new UpdateTIFragment();
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
        View view = inflater.inflate(R.layout.fragment_updateti, container, false);
        upeditTextTI1 = view.findViewById(R.id.upeditTextTI1);
        upeditTextTI2 = view.findViewById(R.id.upeditTextTI2);
        upeditTextTI3 = view.findViewById(R.id.upeditTextTI3);
        upeditTextTI4 = view.findViewById(R.id.upeditTextTI4);
        upeditTextTI5 = view.findViewById(R.id.upeditTextTI5);
        updateuser1 = view.findViewById(R.id.updateuser1);
        updateuser1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int Var_userid = 0;
                try {
                    Var_userid = Integer.parseInt(upeditTextTI1.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }
                String Var_TripInfo_City = upeditTextTI2.getText().toString();
                String TravelAgency_Country = upeditTextTI3.getText().toString();
                String TravelAgency_TripDuration = upeditTextTI4.getText().toString();
                String TravelAgency_TripType = upeditTextTI5.getText().toString();

                try {
                    TripInfo tripInfo = new TripInfo();
                    tripInfo.setId(Var_userid);
                    tripInfo.setCity(Var_TripInfo_City);
                    tripInfo.setCountry(TravelAgency_Country);
                    tripInfo.setTripduration(TravelAgency_TripDuration);
                    tripInfo.setTriptype(TravelAgency_TripType);
                    MainActivity.travelGuideDatabase.travelGuideDao().updateTripInfo(tripInfo);
                    Toast.makeText(getActivity(), "One record updated", Toast.LENGTH_LONG).show();
                }catch(Exception e){
                    String message = e.getMessage();
                    Toast.makeText(getActivity(),message,Toast.LENGTH_LONG).show();
                }

                upeditTextTI1.setText("");
                upeditTextTI2.setText("");
                upeditTextTI3.setText("");
                upeditTextTI4.setText("");
                upeditTextTI5.setText("");
            }
        });
        return view;
    }
}