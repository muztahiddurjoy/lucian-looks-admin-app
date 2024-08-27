package com.epizy.muztahiddurjoy.lucianlooksadminapp;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
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
import com.epizy.muztahiddurjoy.lucianlooksadminapp.Dataset.ServiceReportDataset;

import java.util.ArrayList;
import java.util.Map;

public class ServiceReportAdapter extends RecyclerView.Adapter<ServiceReportAdapter.ServiceReportViewHolder> {
    ArrayList<ServiceReportDataset> arrayList;
    ArrayList<String> keys;
    Context context;
    Map<String, PartnerDataset> serowners;
    ArrayList<String> serownerkeys;

    public ServiceReportAdapter(ArrayList<ServiceReportDataset> arrayList, ArrayList<String> keys, Context context, Map<String, PartnerDataset> serowners, ArrayList<String> serownerkeys) {
        this.arrayList = arrayList;
        this.keys = keys;
        this.context = context;
        this.serowners = serowners;
        this.serownerkeys = serownerkeys;
    }

    @NonNull
    @Override
    public ServiceReportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ServiceReportViewHolder(LayoutInflater.from(context).inflate(R.layout.serreprow,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceReportViewHolder holder, int position) {
            PartnerDataset partnerDataset = serowners.get(serownerkeys.get(position));
            ServiceReportDataset dataset = arrayList.get(position);
            holder.nameRepSer.setText(dataset.getName());
            holder.detailsRepSer.setText(dataset.getDescription());
            holder.sername.setText(dataset.getSername());
            holder.serdes.setText(dataset.getDescription());
            holder.serowner.setText(partnerDataset.getFullname());
            holder.serid.setText(dataset.getSerid());
            holder.serprice.setText(dataset.getSerprice());
            Glide.with(context).load(dataset.getSerimage()).into(holder.serimage);
            holder.copyEmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ClipboardManager manager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData data = ClipData.newPlainText("partnerEmail",partnerDataset.getEmail());
                    manager.setPrimaryClip(data);
                    Toast.makeText(context, "Owner email copied", Toast.LENGTH_SHORT).show();
                }
            });
            holder.copyPhone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ClipboardManager manager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData data = ClipData.newPlainText("partnerPhone",partnerDataset.getPhonenumber());
                    manager.setPrimaryClip(data);
                    Toast.makeText(context, "Owner phone number copied", Toast.LENGTH_SHORT).show();
                }
            });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ServiceReportViewHolder extends RecyclerView.ViewHolder {
        public TextView nameRepSer, detailsRepSer, sername,serdes, serid, serowner, serprice;
        public ImageView serimage;
        public Button copyEmail, copyPhone;
        public ServiceReportViewHolder(@NonNull View itemView) {
            super(itemView);
            nameRepSer = itemView.findViewById(R.id.name_serrep);
            detailsRepSer = itemView.findViewById(R.id.details_serrep);
            sername = itemView.findViewById(R.id.sername_serrep);
            serdes = itemView.findViewById(R.id.serdes_serrep);
            serid = itemView.findViewById(R.id.serid_serrep);
            serowner = itemView.findViewById(R.id.serowner_serrep);
            serprice = itemView.findViewById(R.id.serprice_serrep);
            serimage = itemView.findViewById(R.id.serimage_serrep);
            copyEmail = itemView.findViewById(R.id.copy_email_owner);
            copyPhone = itemView.findViewById(R.id.copy_phone_owner);
        }
    }
}
