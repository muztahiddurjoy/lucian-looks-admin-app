package com.epizy.muztahiddurjoy.lucianlooksadminapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epizy.muztahiddurjoy.lucianlooksadminapp.Dataset.PartnerDataset;
import com.epizy.muztahiddurjoy.lucianlooksadminapp.Dataset.ProReportDataset;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ProductReportFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public ProductReportFragment() {

    }


    public static ProductReportFragment newInstance(String param1, String param2) {
        ProductReportFragment fragment = new ProductReportFragment();
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

    private RecyclerView recyclerView;
    private ArrayList<ProReportDataset> arrayList;
    private ArrayList<String> keys;
    private DatabaseReference reference;
    private DatabaseReference referenceOwners;
    private ProductReportAdapter adapter;
    private Map<String, PartnerDataset> owners;
    private ArrayList<String> ownerkeys;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_product_report, container, false);
        recyclerView = root.findViewById(R.id.recycler_product_report);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setReverseLayout(true);
        recyclerView.setLayoutManager(manager);
        arrayList = new ArrayList<ProReportDataset>();
        owners = new HashMap<String , PartnerDataset>();
        keys = new ArrayList<String>();
        ownerkeys = new ArrayList<String>();
        reference = FirebaseDatabase.getInstance().getReference().child("proreports");
        referenceOwners = FirebaseDatabase.getInstance().getReference().child("partners");
        adapter = new ProductReportAdapter(arrayList,owners,ownerkeys,keys,getActivity());

        referenceOwners.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                owners.clear();
                for(DataSnapshot ds: snapshot.getChildren()){
                    PartnerDataset dataset = ds.getValue(PartnerDataset.class);
                    if (!owners.containsKey(ds.getKey())){
                        owners.put(ds.getKey(),dataset);
                        ownerkeys.add(ds.getKey());
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
                    ProReportDataset dataset = ds.getValue(ProReportDataset.class);
                    String key = ds.getKey();
                    if (!arrayList.contains(dataset)){
                        arrayList.add(dataset);
                        keys.add(key);
                    }
                }
                adapter.notifyDataSetChanged();
                recyclerView.smoothScrollToPosition(arrayList.size());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        recyclerView.setAdapter(adapter);

        return root;
    }
}