package com.epizy.muztahiddurjoy.lucianlooksadminapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.epizy.muztahiddurjoy.lucianlooksadminapp.Dataset.ProductDataset;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    ArrayList<ProductDataset> arrayList;
    ArrayList<String> keys;
    ArrayList<String> admins;
    Context context;
    HashMap<String, PartnerDataset> owners;
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("products");
    DatabaseReference emailreference = FirebaseDatabase.getInstance().getReference().child("basicinfo");
    String email_api_key;

    public ProductAdapter(ArrayList<ProductDataset> arrayList, ArrayList<String> keys, Context context, HashMap<String, PartnerDataset> owners) {
        this.arrayList = arrayList;
        this.keys = keys;
        this.context = context;
        this.owners = owners;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        emailreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                email_api_key = (String) snapshot.child("emailkey").getValue();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return new ProductViewHolder(LayoutInflater.from(context).inflate(R.layout.productrow,parent,false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, @SuppressLint("RecyclerView") int position) {

        ProductDataset dataset = arrayList.get(position);
        PartnerDataset dataset1 = owners.get(dataset.getOwner());
        holder.namePro.setText(dataset.getName());
        holder.descPro.setText(dataset.getDescription());
        Glide.with(context).load(dataset.getImageurl()).into(holder.imagePro);
        holder.idPro.setText("Product ID: "+String.valueOf(dataset.getId()));
        holder.catPro.setText("Category: "+dataset.getCategory());
        holder.ownerPro.setText("Owner: " + dataset1.getFullname());
        holder.pricePro.setText(String.valueOf(dataset.getPrice())+"$");
        holder.deleteProduct.setOnClickListener((v)->{
            new AlertDialog.Builder(context).
                    setTitle("Are You Sure?")
                    .setMessage("Do you really want to delete the product?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            reference.child(keys.get(position)).setValue(null).addOnSuccessListener((s)-> {
                                Toast.makeText(context, "Product Deleted", Toast.LENGTH_SHORT).show();
                            });
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    })
                    .show();
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView namePro, ownerPro, pricePro, catPro, idPro, descPro;
        ImageView imagePro;
        Button deleteProduct;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            namePro = itemView.findViewById(R.id.name_product);
            ownerPro = itemView.findViewById(R.id.owner_product);
            pricePro = itemView.findViewById(R.id.price_product);
            catPro = itemView.findViewById(R.id.category_product);
            idPro = itemView.findViewById(R.id.id_product);
            imagePro = itemView.findViewById(R.id.image_product);
            descPro = itemView.findViewById(R.id.desc_product);
            deleteProduct = itemView.findViewById(R.id.delete_product_button);
        }
    }
}
