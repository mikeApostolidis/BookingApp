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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UpdateCustomerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpdateCustomerFragment extends Fragment {
    EditText oldName,oldSurname,oldTravelPackageId,
            newName,
            newSurname,
            newAge,
            newHotel,
            newTravelPackageId;

    Button updateBtn,returnBtn;
    FirebaseFirestore db;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UpdateCustomerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UpdateCustomerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UpdateCustomerFragment newInstance(String param1, String param2) {
        UpdateCustomerFragment fragment = new UpdateCustomerFragment();
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
        View view = inflater.inflate(R.layout.fragment_update_customer, container, false);

        //Old values of customer to find in firestore
        oldName = view.findViewById(R.id.old_Customer_name);
        oldSurname = view.findViewById(R.id.old_Customer_surname);
        oldTravelPackageId = view.findViewById(R.id.old_travel_package);

        //New values of customer to replace the old values in firestore
        newName=view.findViewById(R.id.Customer_name);
        newSurname=view.findViewById(R.id.Customer_surname);
        newAge = view.findViewById(R.id.Customer_age);
        newHotel = view.findViewById(R.id.hotel);
        newTravelPackageId=view.findViewById(R.id.travel_package);

        //buttons
        updateBtn=view.findViewById(R.id.updateCustomer);
        returnBtn=view.findViewById(R.id.Back_to_manager);
        db=FirebaseFirestore.getInstance();

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //old values
                String var_oldName=oldName.getText().toString();
                String var_oldSurname=oldSurname.getText().toString();
                int var_old_travelpackageid=Integer.parseInt(oldTravelPackageId.getText().toString());


                int var_newtpId = 0;//travel package id
                int var_newage=0;//customer age

                try {
                    var_newtpId = Integer.parseInt(newTravelPackageId.getText().toString());
                    var_newage = Integer.parseInt(newAge.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }
                String var_newname = newName.getText().toString();
                String var_newsurname = newSurname.getText().toString();
                String var_newhotel=newHotel.getText().toString();

                Map<String, Object> Customer = new HashMap<>();
                Customer.put("Age", var_newage);
                Customer.put("Hotel", var_newhotel);
                Customer.put("Name", var_newname);
                Customer.put("Surname",var_newsurname);
                Customer.put("TravelPackageId",var_newtpId);

                //Toast.makeText(getActivity(),db.collection("Customer").toString(),Toast.LENGTH_SHORT).show();

                //klhsh ths methodou update
                UpdateData(var_oldName,var_oldSurname,var_old_travelpackageid,Customer);
                //DeleteData(var_oldName,var_oldSurname);

            }
        });
        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container, new CustomerManageFragment()).addToBackStack(null).commit();
            }
        });
        return view;
    }

    private void UpdateData(String var_oldname,String var_oldsurname,int var_oldTravelPackageId,Map<String, Object> Customer){
        //System.out.println(var_oldname+" "+var_oldsurname+" "+var_oldTravelPackageId+" "+ Customer.toString());
        Toast.makeText(getActivity(),var_oldname+" "+var_oldsurname+" "+var_oldTravelPackageId+" "+ Customer.toString(),Toast.LENGTH_SHORT).show();
        db.collection("Customer")
                .whereEqualTo("Name",var_oldname)
                .whereEqualTo("Surname",var_oldsurname)
                //.whereEqualTo("TravelPackageId",var_oldTravelPackageId)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

//&& !task.getResult().isEmpty()
                if(task.isSuccessful()&& !task.getResult().isEmpty() ){
                    DocumentSnapshot documentSnapshot=task.getResult().getDocuments().get(0);
                    String documentID=documentSnapshot.getId();
                    db.collection("Customer")
                            .document(documentID)
                            .update(Customer)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(getActivity(),"Successfuly updated customer",Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(),"There was an error",Toast.LENGTH_LONG).show();
                        }
                    });
                }else{

                    Toast.makeText(getActivity(),"Failed",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


}