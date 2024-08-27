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
import com.epizy.muztahiddurjoy.lucianlooksadminapp.Dataset.ServiceDataset;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ServicesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ServicesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ServicesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ServicesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ServicesFragment newInstance(String param1, String param2) {
        ServicesFragment fragment = new ServicesFragment();
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

    private RecyclerView  recyclerView;
    private ArrayList<ServiceDataset> arrayList;
    private ArrayList<String> keys;
    private HashMap<String, PartnerDataset> owners;
    private DatabaseReference reference;
    private DatabaseReference ownerrefer;
    private ServicesAdapter servicesAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_services, container, false);
        recyclerView = root.findViewById(R.id.recycler_service);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setReverseLayout(true);
        recyclerView.setLayoutManager(manager);
        arrayList = new ArrayList<ServiceDataset>();
        keys = new ArrayList<String>();
        owners = new HashMap<String,PartnerDataset>();
        reference = FirebaseDatabase.getInstance().getReference().child("services");
        servicesAdapter = new ServicesAdapter(arrayList,keys,owners,getActivity());
        ownerrefer = FirebaseDatabase.getInstance().getReference().child("partners");
        ownerrefer.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                owners.clear();
                for (DataSnapshot ds: snapshot.getChildren()){
                    PartnerDataset partnerDataset = ds.getValue(PartnerDataset.class);
                    owners.put(ds.getKey(),partnerDataset);
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
                for (DataSnapshot ds: snapshot.getChildren()){
                    ServiceDataset dataset = ds.getValue(ServiceDataset.class);
                    if (!arrayList.contains(dataset)){
                        arrayList.add(dataset);
                        keys.add(ds.getKey());
                    }
                    servicesAdapter.notifyDataSetChanged();
                    recyclerView.smoothScrollToPosition(arrayList.size());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        recyclerView.setAdapter(servicesAdapter);
        return root;
    }
}