package com.example.gotouringv2;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

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
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DeleteCustomerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DeleteCustomerFragment extends Fragment {
    EditText name,surname;
    Button deleteCustomerBtn,returnBtn;
    FirebaseFirestore db;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DeleteCustomerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DeleteCustomerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DeleteCustomerFragment newInstance(String param1, String param2) {
        DeleteCustomerFragment fragment = new DeleteCustomerFragment();
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
        db=FirebaseFirestore.getInstance();

        View view = inflater.inflate(R.layout.fragment_delete_customer, container, false);
        name = view.findViewById(R.id.Customer_name);
        surname = view.findViewById(R.id.Customer_surname);
        deleteCustomerBtn=view.findViewById(R.id.deletecustomer);
        returnBtn=view.findViewById(R.id.Back_to_manager);
        deleteCustomerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String var_name=name.getText().toString();
                String var_surname=surname.getText().toString();
                name.setText("");
                surname.setText("");
                DeleteData(var_name,var_surname);
            }

        });
        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container, new CustomerManageFragment()).addToBackStack(null).commit();
            }
        });
        
        // Inflate the layout for this fragment
        return view;
    }
    private void DeleteData(String var_name,String var_surname){
        db.collection("Customer")
                .whereEqualTo("Name",var_name)
                .whereEqualTo("Surname",var_surname)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//
                if(task.isSuccessful() && !task.getResult().isEmpty()){
                    DocumentSnapshot documentSnapshot=task.getResult().getDocuments().get(0);
                    String documentID=documentSnapshot.getId();
                    db.collection("Customer")
                            .document(documentID)
                            .delete()
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(getActivity(),"Successfuly deleted customer",Toast.LENGTH_SHORT).show();
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