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
import com.example.gotouringv2.Entities.TripInfo;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DeleteTPFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DeleteTPFragment extends Fragment {
    EditText editTextdel2;
    Button deluser2;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DeleteTPFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DeleteTPFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DeleteTPFragment newInstance(String param1, String param2) {
        DeleteTPFragment fragment = new DeleteTPFragment();
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

        View view = inflater.inflate(R.layout.fragment_deletetp, container, false);
        editTextdel2 = view.findViewById(R.id.editTextdel2);
        deluser2 = view.findViewById(R.id.deluser2);
        deluser2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int Var_userid = 0;
                try {
                    Var_userid = Integer.parseInt(editTextdel2.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }
                TravelPackage travelPackage = new TravelPackage();
                travelPackage.setId(Var_userid);
                MainActivity.travelGuideDatabase.travelGuideDao().deleteTravelPackage(travelPackage);
                Toast.makeText(getActivity(),"Trip Package deleted ",Toast.LENGTH_LONG).show();
                editTextdel2.setText("");
            }
        });
        return view;
    }
}