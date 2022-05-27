package com.example.gotouringv2;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InsertCustomerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InsertCustomerFragment extends Fragment {
    EditText name,surname,age,hotel,TravelPackageId;
    Button insert;
    FirebaseFirestore db;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InsertCustomerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InsertCustomerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InsertCustomerFragment newInstance(String param1, String param2) {
        InsertCustomerFragment fragment = new InsertCustomerFragment();
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
        View view = inflater.inflate(R.layout.fragment_insert_customer, container, false);
        name = view.findViewById(R.id.Customer_name);
        surname = view.findViewById(R.id.Customer_surname);
        age = view.findViewById(R.id.Customer_age);
        hotel = view.findViewById(R.id.hotel);
        TravelPackageId = view.findViewById(R.id.travel_package);
        // Inflate the layout for this fragment
        insert = view.findViewById(R.id.submitCustomer);
        db=FirebaseFirestore.getInstance();
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int var_tpId = 0;//travel package id
                int var_age=0;//customer age

                try {
                    var_tpId = Integer.parseInt(TravelPackageId.getText().toString());
                    var_age = Integer.parseInt(age.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }
                String var_name = name.getText().toString();
                String var_surname = surname.getText().toString();
                String var_hotel=hotel.getText().toString();
                Map<String, Object> Customer = new HashMap<>();
                Customer.put("Age", var_age);
                Customer.put("Hotel", var_hotel);
                Customer.put("Name", var_name);
                Customer.put("Surname",var_surname);
                Customer.put("TravelPackageId",var_tpId);
                try{
                    db.collection("Customer")
                            .add(Customer)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w(TAG, "Error adding document", e);
                                }
                            });


                }catch (Exception e){
                    String message =  e.getMessage();
                    Toast.makeText(getActivity(),message,Toast.LENGTH_LONG).show();
                }

                name.setText("");
                surname.setText("");
                hotel.setText("");
                age.setText("");
                TravelPackageId.setText("");
            }
        });
        return view;
//       return inflater.inflate(R.layout.fragment_insert_customer, container, false);
    }
}