package com.epizy.muztahiddurjoy.lucianlooksadminapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class AuthenticiationActivity extends AppCompatActivity {
    private TextInputEditText email,pass;
    private Button signin;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authenticiation);
        email = findViewById(R.id.auth_email);
        pass = findViewById(R.id.auth_pass);
        auth = FirebaseAuth.getInstance();
        signin = findViewById(R.id.auth_login);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = email.getText().toString();
                String password  = pass.getText().toString().trim();
                auth.signInWithEmailAndPassword(mail,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        startActivity(new Intent(AuthenticiationActivity.this,MainActivity.class));
                        Snackbar.make(v,"Logged in Successfully", BaseTransientBottomBar.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {
                        Snackbar.make(v,e.getLocalizedMessage(),BaseTransientBottomBar.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}