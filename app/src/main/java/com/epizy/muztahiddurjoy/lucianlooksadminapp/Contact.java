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

import com.epizy.muztahiddurjoy.lucianlooksadminapp.Dataset.ContactDataset;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Contact extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Contact() {
        // Required empty public constructor
    }

    public static Contact newInstance(String param1, String param2) {
        Contact fragment = new Contact();
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
    private ArrayList<ContactDataset> arrayList;
    private DatabaseReference reference;
    private FirebaseRecyclerOptions<ContactDataset> options;
    private FirebaseRecyclerAdapter<ContactDataset,ContactViewHolder> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_contact, container, false);
        recyclerView = root.findViewById(R.id.recycler_contact);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setReverseLayout(true);
        recyclerView.setLayoutManager(manager);
        arrayList = new ArrayList<ContactDataset>();
        reference = FirebaseDatabase.getInstance().getReference().child("contact");
        options = new FirebaseRecyclerOptions.Builder<ContactDataset>().setQuery(reference,ContactDataset.class).build();
        adapter = new FirebaseRecyclerAdapter<ContactDataset, ContactViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull @NotNull ContactViewHolder holder, int position, @NonNull @NotNull ContactDataset model) {
                holder.name_contact.setText(model.getName());
                holder.email_contact.setText(model.getEmail());
                holder.number_contact.setText(model.getNumber());
                holder.message_contact.setText(model.getMessage());
                holder.email_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText("email", model.getEmail());
                        clipboard.setPrimaryClip(clip);
                        Snackbar.make(v,"E-mail coppied", BaseTransientBottomBar.LENGTH_SHORT).show();
                    }
                });
                holder.number_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ClipboardManager clipboardManager = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData data = ClipData.newPlainText("phone", model.getNumber());
                        clipboardManager.setPrimaryClip(data);
                        Snackbar.make(v,"Phone number coppied", BaseTransientBottomBar.LENGTH_SHORT).show();
                    }
                });
            }

            @NonNull
            @NotNull
            @Override
            public ContactViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
                return new ContactViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.contact_row,parent,false));
            }
        };
        recyclerView.setAdapter(adapter);
        return root;
    }

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
}