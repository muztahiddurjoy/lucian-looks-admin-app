package com.epizy.muztahiddurjoy.lucianlooksadminapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epizy.muztahiddurjoy.lucianlooksadminapp.Dataset.PartnerDataset;
import com.epizy.muztahiddurjoy.lucianlooksadminapp.Dataset.ServiceReportDataset;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ServiceReportFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ServiceReportFragment() {
        // Required empty public constructor
    }


    public static ServiceReportFragment newInstance(String param1, String param2) {
        ServiceReportFragment fragment = new ServiceReportFragment();
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

    private RecyclerView recyclerViewl;
    private ArrayList<ServiceReportDataset> arrayList;
    private ArrayList<String> keys;
    private DatabaseReference reference;
    private DatabaseReference serownersdatabase;
    private ServiceReportAdapter adapter;
    private Map<String, PartnerDataset> serowners;
    private ArrayList<String> serownerkeys;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_service_report, container, false);
        recyclerViewl = root.findViewById(R.id.recycler_service_report);
        recyclerViewl.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setReverseLayout(true);
        recyclerViewl.setLayoutManager(manager);
        arrayList = new ArrayList<ServiceReportDataset>();
        keys = new ArrayList<String>();
        serowners = new HashMap<String,PartnerDataset>();
        serownerkeys = new ArrayList<String>();
        adapter = new ServiceReportAdapter(arrayList,keys,getActivity(),serowners,serownerkeys);
        reference = FirebaseDatabase.getInstance().getReference().child("sereports");
        serownersdatabase = FirebaseDatabase.getInstance().getReference().child("partners");
        serownersdatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                serownerkeys.clear();
                serowners.clear();
                for (DataSnapshot ds : snapshot.getChildren()){
                    PartnerDataset partnerDataset = ds.getValue(PartnerDataset.class);
                    String key = ds.getKey();
                    if (!serowners.containsKey(key)){
                        serowners.put(key,partnerDataset);
                        serownerkeys.add(key);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                keys.clear();
                    for (DataSnapshot ds : snapshot.getChildren()){
                        ServiceReportDataset reportDataset = ds.getValue(ServiceReportDataset.class);
                        String key = ds.getKey();
                        if (!arrayList.contains(reportDataset)){
                            arrayList.add(reportDataset);
                            keys.add(key);
                        }
                    }
                    adapter.notifyDataSetChanged();
                    recyclerViewl.smoothScrollToPosition(arrayList.size());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        recyclerViewl.setAdapter(adapter);
        return root;
    }
}