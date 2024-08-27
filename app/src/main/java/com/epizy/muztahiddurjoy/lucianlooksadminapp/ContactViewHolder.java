package com.epizy.muztahiddurjoy.lucianlooksadminapp;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ContactViewHolder extends RecyclerView.ViewHolder {
    public TextView name_contact, email_contact, number_contact, message_contact;
    public Button email_btn,number_btn;
    public ContactViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        name_contact = itemView.findViewById(R.id.name_contact);
        email_contact = itemView.findViewById(R.id.email_contact);
        number_contact = itemView.findViewById(R.id.number_contact);
        message_contact = itemView.findViewById(R.id.message_contact);
        email_btn = itemView.findViewById(R.id.copy_email);
        number_btn = itemView.findViewById(R.id.copy_number);
    }
}
