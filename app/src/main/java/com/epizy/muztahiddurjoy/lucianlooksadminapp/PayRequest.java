package com.epizy.muztahiddurjoy.lucianlooksadminapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epizy.muztahiddurjoy.lucianlooksadminapp.Dataset.PayRequestDataset;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PayRequest extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public PayRequest() {

    }

    public static PayRequest newInstance(String param1, String param2) {
        PayRequest fragment = new PayRequest();
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
    private ArrayList<PayRequestDataset> arrayList;
    private ArrayList<String> keys;
    private DatabaseReference reference;
    private PaymentRequestViewHolder adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_pay_request, container, false);
        recyclerView = root.findViewById(R.id.recycler_pay_request);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        arrayList = new ArrayList<PayRequestDataset>();
        keys = new ArrayList<String>();
        adapter = new PaymentRequestViewHolder(arrayList,keys,getActivity());
        reference = FirebaseDatabase.getInstance().getReference().child("payRequest");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                keys.clear();
                for (DataSnapshot ds : snapshot.getChildren()){
                    PayRequestDataset req = ds.getValue(PayRequestDataset.class);
                    String key = snapshot.getKey();
                    if (!arrayList.contains(req)){
                        arrayList.add(req);
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