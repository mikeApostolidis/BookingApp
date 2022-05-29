package com.example.gotouringv2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gotouringv2.Entities.TravelAgency;
import com.example.gotouringv2.Entities.TravelPackage;
import com.example.gotouringv2.Entities.TripInfo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GetAllCustomers#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GetAllCustomers extends Fragment {
    ArrayAdapter<CharSequence> adapter;
    int temp;
    Spinner spinner;
    TextView listOfCustomers;
    Button runQueryBtn,returnBtn;
    FirebaseFirestore db;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GetAllCustomers() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GetAllCustomers.
     */
    // TODO: Rename and change types and number of parameters
    public static GetAllCustomers newInstance(String param1, String param2) {
        GetAllCustomers fragment = new GetAllCustomers();
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
        View view =inflater.inflate(R.layout.fragment_get_all_customers, container, false);

        spinner = view.findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(getContext(), R.array.customer_querries_array, R.layout.support_simple_spinner_dropdown_item );//, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        listOfCustomers=view.findViewById(R.id.customerQuery);
        runQueryBtn=view.findViewById(R.id.runCustomerQueries);
        returnBtn=view.findViewById(R.id.returnButton);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){

                temp=position+1;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        runQueryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(temp){
                    //case 1-3 show all
                    case 1:
                        db.collection("Customer")
                                .get()
                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        String result = "";
                                        if(task.isSuccessful() && !task.getResult().isEmpty()){

                                            Toast.makeText(getActivity(),"Query successful",Toast.LENGTH_SHORT).show();

                                            for(QueryDocumentSnapshot document:task.getResult()) {
                                                result = result + "\n"+"name:" + document.get("Name")+
                                                        "\n"+"surname:" + document.get("Surname")+
                                                        "\n"+"age:"+document.get("Age")+
                                                        "\n"+"hotel:"+document.get("Hotel")+
                                                        "\n"+"travel package:"+document.get("TravelPackageId")+"\n";
                                            }
                                            listOfCustomers.setText(result);

                                        }else{
                                            Toast.makeText(getActivity(),"Query failed",Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                });
                        break;
                    case 2:
                        db.collection("Customer")
                                .whereGreaterThan("Age",30)
                                .get()
                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        String result = "";
                                        if(task.isSuccessful() && !task.getResult().isEmpty()){

                                            Toast.makeText(getActivity(),"Query successful",Toast.LENGTH_SHORT).show();

                                            for(QueryDocumentSnapshot document:task.getResult()) {
                                                result = result + "\n"+"name:" + document.get("Name")+
                                                        "\n"+"surname:" + document.get("Surname")+
                                                        "\n"+"age:"+document.get("Age")+
                                                        "\n"+"hotel:"+document.get("Hotel")+
                                                        "\n"+"travel package:"+document.get("TravelPackageId")+"\n";
                                            }
                                            listOfCustomers.setText(result);

                                        }else{
                                            Toast.makeText(getActivity(),"Query failed",Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                });

                        break;
                    case 3:
                        db.collection("Customer")
                                .get()
                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        String result = "";
                                        int count=0;
                                        if(task.isSuccessful() && !task.getResult().isEmpty()){

                                            Toast.makeText(getActivity(),"Query successful",Toast.LENGTH_SHORT).show();

                                            for(QueryDocumentSnapshot document:task.getResult()) {
                                                if(document.get("Name").toString().startsWith("A")){
                                                    result = result + "\n"+"name:" + document.get("Name")+
                                                            "\n"+"surname:" + document.get("Surname")+
                                                            "\n"+"age:"+document.get("Age")+
                                                            "\n"+"hotel:"+document.get("Hotel")+
                                                            "\n"+"travel package:"+document.get("TravelPackageId")+"\n";
                                                            count++;
                                                }

                                            }
                                            result=result+"\n"+"the number of customers is:"+count;
                                            listOfCustomers.setText(result);

                                        }else{
                                            Toast.makeText(getActivity(),"Query failed",Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                });
                        break;
                    case 4:
                        db.collection("Customer")
                                //.whereEqualTo("Hotel","Grand Hotel")
                                .whereLessThan("Age",30)
                                .get()
                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        String result = "";
                                        if(task.isSuccessful() && !task.getResult().isEmpty()){

                                            Toast.makeText(getActivity(),"Query successful",Toast.LENGTH_SHORT).show();

                                            for(QueryDocumentSnapshot document:task.getResult()) {
                                                if(document.get("Hotel").toString().equals("Grand Hotel")){
                                                    result = result + "\n"+"name:" + document.get("Name")+
                                                            "\n"+"surname:" + document.get("Surname")+
                                                            "\n"+"age:"+document.get("Age")+
                                                            "\n"+"hotel:"+document.get("Hotel")+
                                                            "\n"+"travel package:"+document.get("TravelPackageId")+"\n";
                                                }

                                            }
                                            listOfCustomers.setText(result);

                                        }else{
                                            Toast.makeText(getActivity(),"Query failed",Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                });
                        break;}



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
}