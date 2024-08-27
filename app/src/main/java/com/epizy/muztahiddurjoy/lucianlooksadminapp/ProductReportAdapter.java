package com.epizy.muztahiddurjoy.lucianlooksadminapp;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.util.Log;
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
import com.epizy.muztahiddurjoy.lucianlooksadminapp.Dataset.ProReportDataset;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProductReportAdapter extends RecyclerView.Adapter<ProductReportAdapter.ProductReportViewHolder> {
    ArrayList<ProReportDataset> arrayList;
    Map<String, PartnerDataset> owners;
    ArrayList<String> ownerkeys;
    ArrayList<String> keys;
    Context context;

    public ProductReportAdapter(ArrayList<ProReportDataset> arrayList, Map<String, PartnerDataset> owners, ArrayList<String> ownerkeys, ArrayList<String> keys, Context context) {
        this.arrayList = arrayList;
        this.owners = owners;
        this.ownerkeys = ownerkeys;
        this.keys = keys;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductReportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductReportViewHolder(LayoutInflater.from(context).inflate(R.layout.proreprow,parent,false));
    }

    public void onBindViewHolder(@NonNull ProductReportViewHolder holder, int position) {
        ProReportDataset dataset = arrayList.get(position);
//        String partnerKey = ownerkeys.get(position);
        PartnerDataset partner = owners.get(dataset.getProwner());
        holder.nameRepo.setText(dataset.getName());
        holder.detailsRep.setText(dataset.getDetails());
        holder.proname.setText(dataset.getName());
        holder.prodes.setText(dataset.getProdes());
        holder.prowner.setText(partner.getFullname());
        holder.proprice.setText(dataset.getProprice()+"$");
        holder.copyBtnProEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager  manager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData data = ClipData.newPlainText("proreportemail",partner.getEmail());
                manager.setPrimaryClip(data);
                Toast.makeText(context, "Owner Email Copied", Toast.LENGTH_SHORT).show();
            }
        });
        holder.copybtnProPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager  manager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData data = ClipData.newPlainText("proreportnumber",partner.getPhonenumber());
                manager.setPrimaryClip(data);
                Toast.makeText(context, "Owner Phone Number  Copied", Toast.LENGTH_SHORT).show();
            }
        });
        Glide.with(context).load(dataset.getProimage()).into(holder.proimage);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ProductReportViewHolder extends RecyclerView.ViewHolder {
        public TextView nameRepo, detailsRep,proname, prodes, proprice, prowner;
        public ImageView proimage;
        public Button copyBtnProEmail , copybtnProPhone;
        public ProductReportViewHolder(@NonNull View itemView) {
            super(itemView);
            nameRepo = itemView.findViewById(R.id.name_pro_report);
            detailsRep = itemView.findViewById(R.id.details_pro_report);
            proname = itemView.findViewById(R.id.proname_pro_report);
            prodes = itemView.findViewById(R.id.prodes_pro_report);
            proprice = itemView.findViewById(R.id.proprice_pro_report);
            prowner = itemView.findViewById(R.id.prowner_pro_report);
            proimage = itemView.findViewById(R.id.proimage_pro_report);
            copyBtnProEmail = itemView.findViewById(R.id.copy_email_proreport);
            copybtnProPhone = itemView.findViewById(R.id.copy_number_proreport);
        }
    }
}
