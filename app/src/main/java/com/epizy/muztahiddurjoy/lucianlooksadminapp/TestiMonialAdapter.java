package com.epizy.muztahiddurjoy.lucianlooksadminapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.epizy.muztahiddurjoy.lucianlooksadminapp.Dataset.TestimonialDataset;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class TestiMonialAdapter extends RecyclerView.Adapter<TestiMonialAdapter.TestiViewHolder> {
    ArrayList<TestimonialDataset> arrayList;
    ArrayList<String> keys;
    Context context;

    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("testimonials");
    public TestiMonialAdapter(ArrayList<TestimonialDataset> arrayList, ArrayList<String> keys, Context context) {
        this.arrayList = arrayList;
        this.keys = keys;
        this.context = context;
    }



    @NonNull
    @Override
    public TestiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TestiViewHolder(LayoutInflater.from(context).inflate(R.layout.testirow,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull TestiViewHolder holder, @SuppressLint("RecyclerView") int position) {
        TestimonialDataset dataset = arrayList.get(position);
        holder.nameTesti.setText(dataset.getName());
        holder.descTesti.setText(dataset.getDesc());
        holder.dateTesti.setText(dataset.getDate());
        holder.deleteTesti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(context).setTitle("Are you sure?").setMessage("are you sure that you want to delete the review?").setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        reference.child(keys.get(position)).setValue(null);
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class TestiViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTesti, descTesti, dateTesti;
        public Button deleteTesti;
        public TestiViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTesti = itemView.findViewById(R.id.name_testi);
            descTesti = itemView.findViewById(R.id.desc_testi);
            dateTesti = itemView.findViewById(R.id.date_testi);
            deleteTesti = itemView.findViewById(R.id.delete_review_testi);
        }
    }
}
