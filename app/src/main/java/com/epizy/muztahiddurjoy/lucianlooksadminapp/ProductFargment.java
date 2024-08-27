package com.epizy.muztahiddurjoy.lucianlooksadminapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.epizy.muztahiddurjoy.lucianlooksadminapp.Dataset.PartnerDataset;
import com.epizy.muztahiddurjoy.lucianlooksadminapp.Dataset.ProductDataset;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;


public class ProductFargment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public ProductFargment() {

    }

    public static ProductFargment newInstance(String param1, String param2) {
        ProductFargment fragment = new ProductFargment();
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
    private ArrayList<ProductDataset> arrayList;
    private ArrayList<String> keys;
    private ArrayList<String> admins;
    private DatabaseReference owneref;
    private DatabaseReference reference;
    private ProductAdapter adapter;
    private HashMap<String, PartnerDataset> owners;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_product_fargment, container, false);
        recyclerView = root.findViewById(R.id.recycler_products);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setReverseLayout(true);
        recyclerView.setLayoutManager(manager);
        arrayList = new ArrayList<ProductDataset>();
        keys = new ArrayList<String>();
        admins = new ArrayList<String>();
        owners = new HashMap<String,PartnerDataset>();
        adapter = new ProductAdapter(arrayList,keys,getActivity(),owners);
        reference = FirebaseDatabase.getInstance().getReference().child("products");
        owneref = FirebaseDatabase.getInstance().getReference().child("partners");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                keys.clear();
                for (DataSnapshot ds : snapshot.getChildren()){
                    String key = ds.getKey();
                    ProductDataset data = ds.getValue(ProductDataset.class);
                    if (!arrayList.contains(data)){
                        arrayList.add(data);
                        keys.add(key);
                        admins.add(data.getOwner());
                    }
                }
                adapter.notifyDataSetChanged();
                recyclerView.smoothScrollToPosition(arrayList.size());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        owneref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                owners.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    PartnerDataset dataset = ds.getValue(PartnerDataset.class);
                    if (!owners.containsKey(dataset)){
                        owners.put(ds.getKey(),dataset);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        recyclerView.setAdapter(adapter);
        return root;
    }
}