package com.epizy.muztahiddurjoy.lucianlooksadminapp;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PartnerViewHolder extends RecyclerView.ViewHolder {
    public TextView namePartner, email, expire, lastpaid, method, phone, payerID, orderID;
    public Button copyEmailPartner, copyPhonePartner;

    public PartnerViewHolder(@NonNull View itemView) {
        super(itemView);
        namePartner = itemView.findViewById(R.id.name_partner);
        email = itemView.findViewById(R.id.email_partner);
        expire = itemView.findViewById(R.id.expire_partner);
        lastpaid = itemView.findViewById(R.id.lastpaid_partner);
        method = itemView.findViewById(R.id.paytmethod_partner);
        phone = itemView.findViewById(R.id.phone_partner);
        payerID = itemView.findViewById(R.id.payer_id_partner);
        orderID = itemView.findViewById(R.id.order_id_partner);
        copyEmailPartner = itemView.findViewById(R.id.copy_email_partner);
        copyPhonePartner = itemView.findViewById(R.id.copy_phone_partner);
    }
}
