package com.epizy.muztahiddurjoy.lucianlooksadminapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SolashScreen extends AppCompatActivity {
private FirebaseAuth auth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() == null){
            startActivity(new Intent(this,AuthenticiationActivity.class));
        }
        else {
            startActivity(new Intent(this,MainActivity.class));
        }
    }
}
