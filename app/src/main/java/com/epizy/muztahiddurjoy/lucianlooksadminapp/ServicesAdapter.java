package com.epizy.muztahiddurjoy.lucianlooksadminapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.epizy.muztahiddurjoy.lucianlooksadminapp.Dataset.PartnerDataset;
import com.epizy.muztahiddurjoy.lucianlooksadminapp.Dataset.ServiceDataset;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ServiceViewHolder> {
    ArrayList<ServiceDataset> arrayList;
    ArrayList<String> keys;
    HashMap<String, PartnerDataset> partners;
    Context context;
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("services");

    public ServicesAdapter(ArrayList<ServiceDataset> arrayList, ArrayList<String> keys, HashMap<String, PartnerDataset> partners, Context context) {
        this.arrayList = arrayList;
        this.keys = keys;
        this.partners = partners;
        this.context = context;
    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ServiceViewHolder(LayoutInflater.from(context).inflate(R.layout.serrow,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ServiceDataset dataset = arrayList.get(position);
        PartnerDataset partner = partners.get(dataset.getOwner());

        holder.serName.setText(dataset.getName());
        holder.serDes.setText(dataset.getDescription());
        holder.serPrice.setText(dataset.getPrice()+"$");
        holder.serID.setText(dataset.getPrice());
        holder.serOwner.setText(partner.getFullname());
        holder.deleteSer.setOnClickListener((v)->{
            new AlertDialog.Builder(context)
                    .setMessage("Do you really want to delete the service?")
                    .setTitle("Are you sure?")
            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    reference.child(keys.get(position)).setValue(null).addOnSuccessListener((s)-> Toast.makeText(context, "Service Deleted", Toast.LENGTH_SHORT).show());
                }
            })
            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            }).show();
        });
        Glide.with(context).load(dataset.getImage()).into(holder.serImage);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ServiceViewHolder extends RecyclerView.ViewHolder {
        public TextView serName, serDes, serPrice, serID, serOwner;
        public Button deleteSer;
        public ImageView serImage;
        public ServiceViewHolder(@NonNull View itemView) {
            super(itemView);
            serName = itemView.findViewById(R.id.service_name);
            serDes = itemView.findViewById(R.id.service_description);
            serPrice = itemView.findViewById(R.id.service_price);
            serID = itemView.findViewById(R.id.service_id);
            serOwner = itemView.findViewById(R.id.service_owner);
            serImage = itemView.findViewById(R.id.service_image);
            deleteSer = itemView.findViewById(R.id.delete_service_button);
        }
    }
}
