package com.epizy.muztahiddurjoy.lucianlooksadminapp;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.epizy.muztahiddurjoy.lucianlooksadminapp.Dataset.PartnerDataset;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PartnerFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public PartnerFragment() {
    }

    public static PartnerFragment newInstance(String param1, String param2) {
        PartnerFragment fragment = new PartnerFragment();
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
    private ArrayList<PartnerDataset> arrayList;
    private FirebaseRecyclerOptions<PartnerDataset> options;
    private DatabaseReference reference;
    private FirebaseRecyclerAdapter<PartnerDataset,PartnerViewHolder> adapter;

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_partner, container, false);
        recyclerView = root.findViewById(R.id.recycler_partner);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        arrayList = new ArrayList<PartnerDataset>();
        reference = FirebaseDatabase.getInstance().getReference().child("partners");
        options = new FirebaseRecyclerOptions.Builder<PartnerDataset>().setQuery(reference,PartnerDataset.class).build();
        adapter = new FirebaseRecyclerAdapter<PartnerDataset, PartnerViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull PartnerViewHolder holder, int position, @NonNull PartnerDataset model) {
                holder.namePartner.setText(model.getFullname());
                holder.email.setText(model.getEmail());
                holder.expire.setText(model.getExpireDate());
                holder.lastpaid.setText(model.getLastpaid());
                holder.phone.setText(model.getPhonenumber());
                holder.method.setText(model.getPaymethod());
                holder.payerID.setText(model.getPayerID());
                holder.orderID.setText(model.getOrderID());
                holder.copyEmailPartner.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ClipboardManager manager = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData data = ClipData.newPlainText("partnermail", model.getEmail());
                        manager.setPrimaryClip(data);
                        Toast.makeText(getActivity(), "Partner Email Copied", Toast.LENGTH_SHORT).show();

                    }
                });
                holder.copyPhonePartner.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ClipboardManager manager = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData datac = ClipData.newPlainText("partnerphone",model.getPhonenumber());
                        manager.setPrimaryClip(datac);
                        Toast.makeText(getActivity(), "Partner Phone Number Copied", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @NonNull
            @Override
            public PartnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new PartnerViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.partnerrow,parent,false));
            }
        };
        recyclerView.setAdapter(adapter);
        return root;
    }
}