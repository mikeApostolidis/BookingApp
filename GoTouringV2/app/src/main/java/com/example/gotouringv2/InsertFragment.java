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
 * Use the {@link InsertFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InsertFragment extends Fragment {

    EditText editText1,editText2,editText3;
    Button addbutton;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InsertFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InsertFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InsertFragment newInstance(String param1, String param2) {
        InsertFragment fragment = new InsertFragment();
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
        View view = inflater.inflate(R.layout.fragment_insert, container, false);
        editText1 = view.findViewById(R.id.editText1);
        editText2 = view.findViewById(R.id.editText2);
        editText3 = view.findViewById(R.id.editText3);

        addbutton = view.findViewById(R.id.submituser);
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int Var_userid = 0;
                try {
                    Var_userid = Integer.parseInt(editText1.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }
                String Var_agencyname = editText2.getText().toString();
                String Var_agencyaddress = editText3.getText().toString();
                //try catch gia elenxo id
                try{
                    TravelAgency travelagency = new TravelAgency();
                    travelagency.setId(Var_userid);
                    travelagency.setName(Var_agencyname);
                    travelagency.setAddress(Var_agencyaddress);
                    MainActivity.travelGuideDatabase.travelGuideDao().insertTravelAgency(travelagency);
                    Toast.makeText(getActivity(),"Ola kala",Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    String message =  e.getMessage();
                    Toast.makeText(getActivity(),message,Toast.LENGTH_LONG).show();
                }

                editText1.setText("");
                editText2.setText("");
                editText3.setText("");
            }
        });
        return view;
    }
}