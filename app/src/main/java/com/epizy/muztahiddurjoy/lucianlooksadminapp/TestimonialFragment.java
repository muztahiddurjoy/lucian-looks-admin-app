package com.epizy.muztahiddurjoy.lucianlooksadminapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.epizy.muztahiddurjoy.lucianlooksadminapp.Dataset.TestimonialDataset;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;


public class TestimonialFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public TestimonialFragment() {

    }

    public static TestimonialFragment newInstance(String param1, String param2) {
        TestimonialFragment fragment = new TestimonialFragment();
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
    private ArrayList<TestimonialDataset> arrayList;
    private ArrayList<String> keys;
    private DatabaseReference reference;
    private TestiMonialAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_testimonial, container, false);
        recyclerView = root.findViewById(R.id.recycler_testimonial);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setReverseLayout(true);
        recyclerView.setLayoutManager(manager);
        arrayList = new ArrayList<TestimonialDataset>();
        keys = new ArrayList<String>();
        reference = FirebaseDatabase.getInstance().getReference().child("testimonials");
        adapter = new TestiMonialAdapter(arrayList,keys,getActivity());
        recyclerView.setAdapter(adapter);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                keys.clear();
                for (DataSnapshot ds: snapshot.getChildren()){
                    TestimonialDataset dataset = ds.getValue(TestimonialDataset.class);
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

        return root;
    }
}