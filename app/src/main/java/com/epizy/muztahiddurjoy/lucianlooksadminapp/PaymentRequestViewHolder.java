package com.epizy.muztahiddurjoy.lucianlooksadminapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.epizy.muztahiddurjoy.lucianlooksadminapp.Dataset.PayRequestDataset;

import java.util.ArrayList;

public class PaymentRequestViewHolder extends RecyclerView.Adapter<PaymentRequestViewHolder.PaymentRequestView> {

    ArrayList<PayRequestDataset> arrayList;
    ArrayList<String> keys;
    Context context;

    public PaymentRequestViewHolder(ArrayList<PayRequestDataset> arrayList, ArrayList<String> keys, Context context) {
        this.arrayList = arrayList;
        this.keys = keys;
        this.context = context;
    }

    @NonNull
    @Override
    public PaymentRequestView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PaymentRequestView(LayoutInflater.from(context).inflate(R.layout.paymentrpw,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentRequestView holder, int position) {
        PayRequestDataset dataset = arrayList.get(position);
        holder.namePay.setText(dataset.getFullname());
        holder.ammoPay.setText(dataset.getAmmo());
        holder.emailPay.setText(dataset.getEmail());
        holder.phonePay.setText(dataset.getPhone());
        holder.timePay.setText(dataset.getTime());
        holder.statusPay.setText(dataset.getStatus());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class PaymentRequestView extends RecyclerView.ViewHolder {
        public TextView namePay, emailPay, phonePay, statusPay, timePay, ammoPay;
        public PaymentRequestView(@NonNull View itemView) {
            super(itemView);
            namePay = itemView.findViewById(R.id.name_pay_req);
            emailPay = itemView.findViewById(R.id.email_pay_req);
            phonePay = itemView.findViewById(R.id.phone_pay_req);
            statusPay = itemView.findViewById(R.id.status_pay_req);
            timePay = itemView.findViewById(R.id.time_pay_req);
            ammoPay = itemView.findViewById(R.id.amount_pay_req);
        }
    }
}
