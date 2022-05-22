package com.example.gotouringv2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gotouringv2.Entities.TravelAgency;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UpdateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpdateFragment extends Fragment {
    EditText upeditText1,upeditText2,upeditText3;
    Button updateuser;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UpdateFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UpdateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UpdateFragment newInstance(String param1, String param2) {
        UpdateFragment fragment = new UpdateFragment();
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
        View view = inflater.inflate(R.layout.fragment_update, container, false);
        upeditText1 = view.findViewById(R.id.upeditText1);
        upeditText2 = view.findViewById(R.id.upeditText2);
        upeditText3 = view.findViewById(R.id.upeditText3);
        updateuser = view.findViewById(R.id.updateuser);
        updateuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int Var_userid = 0;
                try {
                    Var_userid = Integer.parseInt(upeditText1.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }
                String Var_agencyname = upeditText2.getText().toString();
                String Var_agencyaddress = upeditText3.getText().toString();
                try{
                    TravelAgency travelagency = new TravelAgency();
                    travelagency.setId(Var_userid);
                    travelagency.setName(Var_agencyname);
                    travelagency.setAddress(Var_agencyaddress);
                    MainActivity.travelGuideDatabase.travelGuideDao().updateTravelAgency(travelagency);
                    Toast.makeText(getActivity(),"One record updated!",Toast.LENGTH_LONG).show();
                }catch(Exception e){
                    String message = e.getMessage();
                    Toast.makeText(getActivity(),message,Toast.LENGTH_LONG).show();
                }

                upeditText1.setText("");
                upeditText2.setText("");
                upeditText3.setText("");
            }
        });
        return view;
    }
}