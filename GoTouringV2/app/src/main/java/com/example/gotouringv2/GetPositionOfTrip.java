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
import com.example.gotouringv2.Entities.TravelGuideDatabase;
import com.example.gotouringv2.Entities.TripInfo;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GetPositionOfTrip#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GetPositionOfTrip extends Fragment {
    EditText editTextdel;
    Button deluser;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GetPositionOfTrip() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GetPositionOfTrip.
     */
    // TODO: Rename and change types and number of parameters
    public static GetPositionOfTrip newInstance(String param1, String param2) {
        GetPositionOfTrip fragment = new GetPositionOfTrip();
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
        View view = inflater.inflate(R.layout.fragment_get_position_of_trip, container, false);
        editTextdel = view.findViewById(R.id.editTextdel);
        deluser = view.findViewById(R.id.deluser);

        //get the position of the city
        deluser.setOnClickListener(this::onClick);
        return view;
    }


    public void onClick(View view) {
        TravelGuideDatabase travelGuideDatabase;
        int Var_userid = 0;
        try {
            Var_userid = Integer.parseInt(editTextdel.getText().toString());
        } catch (NumberFormatException ex) {
            System.out.println("Could not parse " + ex);
        }
        TripInfo triplInfo = new TripInfo();
        triplInfo.setId(Var_userid);
        String cityToFind=triplInfo.getCity();

        Bundle bundle =new Bundle();
        bundle.putString("key",cityToFind);
        MapsFragment mf=new MapsFragment();
        mf.setArguments(bundle);
        Toast.makeText(getActivity(),"ALL GOOOOOOD ",Toast.LENGTH_LONG).show();
        getParentFragmentManager().beginTransaction().replace(R.id.fragment_container, mf).addToBackStack(null).commit();

        //Toast.makeText(getActivity(),"User deleted ",Toast.LENGTH_LONG).show();
        editTextdel.setText("");
    }
}