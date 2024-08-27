package com.epizy.muztahiddurjoy.lucianlooksadminapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class PlansPrice extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public PlansPrice() {

    }

    public static PlansPrice newInstance(String param1, String param2) {
        PlansPrice fragment = new PlansPrice();
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

    private TextInputEditText monthly, threeMonthly, sixMonthly, yearly;
    private Button updateMonthly, updateThreeMonthly, updateSixMonthly, updateYearly;
    private DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_plans_price, container, false);
        monthly = root.findViewById(R.id.price_monthly);
        threeMonthly = root.findViewById(R.id.price_three_monthly);
        sixMonthly = root.findViewById(R.id.price_six_monthly);
        yearly = root.findViewById(R.id.price_yearly);

        updateMonthly = root.findViewById(R.id.price_update_monthly);
        updateThreeMonthly = root.findViewById(R.id.price_update_three_monthly);
        updateSixMonthly = root.findViewById(R.id.price_update_six_monthly);
        updateYearly = root.findViewById(R.id.price_update_yearly);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("plansPrice");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                monthly.setText(String.valueOf(snapshot.child("monthly").getValue()));
                threeMonthly.setText(String.valueOf(snapshot.child("three_monthly").getValue()));
                sixMonthly.setText(String.valueOf(snapshot.child("six_monthly").getValue()));
                yearly.setText(String.valueOf(snapshot.child("yearly").getValue()));
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        updateMonthly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String monthlyS = monthly.getText().toString();
                databaseReference.child("monthly").setValue(monthlyS).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Snackbar.make(v,"Updated Successfully", BaseTransientBottomBar.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        updateThreeMonthly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String threeMonthlyS = threeMonthly.getText().toString();
                databaseReference.child("three_monthly").setValue(threeMonthlyS).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Snackbar.make(v,"Updated Successfully", BaseTransientBottomBar.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        updateSixMonthly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sixMonthlyS = sixMonthly.getText().toString();
                databaseReference.child("six_monthly").setValue(sixMonthlyS).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Snackbar.make(v,"Updated Successfully", BaseTransientBottomBar.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        updateYearly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String yearlyS = yearly.getText().toString();
                databaseReference.child("yearly").setValue(yearlyS).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Snackbar.make(v,"Updated Successfully", BaseTransientBottomBar.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        return root;
    }
}