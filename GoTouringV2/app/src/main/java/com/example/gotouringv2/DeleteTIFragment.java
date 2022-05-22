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
import com.example.gotouringv2.Entities.TripInfo;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DeleteTIFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DeleteTIFragment extends Fragment {
    EditText editTextdel1;
    Button deluser1;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DeleteTIFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DeleteTIFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DeleteTIFragment newInstance(String param1, String param2) {
        DeleteTIFragment fragment = new DeleteTIFragment();
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
        View view = inflater.inflate(R.layout.fragment_deleteti, container, false);
        editTextdel1 = view.findViewById(R.id.editTextdel1);
        deluser1 = view.findViewById(R.id.deluser1);
        deluser1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int Var_userid = 0;
                try {
                    Var_userid = Integer.parseInt(editTextdel1.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }
                TripInfo tripInfo = new TripInfo();
                tripInfo.setId(Var_userid);
                MainActivity.travelGuideDatabase.travelGuideDao().deleteTripInfo(tripInfo);
                Toast.makeText(getActivity(),"Trip Info deleted ",Toast.LENGTH_LONG).show();
                editTextdel1.setText("");

            }
        });
        return view;
    }
}