package com.example.gotouringv2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CustomerManageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CustomerManageFragment extends Fragment implements View.OnClickListener{
    Button Bn_insert, Bn_delete, Bn_update, Bn_Query;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CustomerManageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CustomerManageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CustomerManageFragment newInstance(String param1, String param2) {
        CustomerManageFragment fragment = new CustomerManageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_manage, container, false);
        Bn_insert = view.findViewById(R.id.insertButton);
        Bn_insert.setOnClickListener(this);
        Bn_delete = view.findViewById(R.id.deleteButton);
        Bn_delete.setOnClickListener(this);
        Bn_update = view.findViewById(R.id.updateButton);
        Bn_update.setOnClickListener(this);
        Bn_Query = view.findViewById(R.id.queryButton);
        Bn_Query.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.insertButton:
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container, new InsertCustomerFragment()).addToBackStack(null).commit();
                break;
            case R.id.deleteButton:
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container, new DeleteCustomerFragment()).addToBackStack(null).commit();
                break;
            case R.id.updateButton:
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container, new UpdateCustomerFragment()).addToBackStack(null).commit();
                break;
            case R.id.queryButton:
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container, new GetAllCustomers()).addToBackStack(null).commit();
                break;
        }
    }


}