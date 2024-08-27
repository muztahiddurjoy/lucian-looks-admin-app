package com.epizy.muztahiddurjoy.lucianlooksadminapp.ui.home;


import static androidx.media.MediaBrowserServiceCompat.RESULT_OK;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.epizy.muztahiddurjoy.lucianlooksadminapp.R;
import com.epizy.muztahiddurjoy.lucianlooksadminapp.databinding.FragmentHomeBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;


    private TextInputEditText description, emailAdd, addressO, phoneNumber, popupText, emailApiKey;
    private Button updateDescription, updateEmail, updateAddress, updateNumber, updatePopup, updateIcon, updateEmailApi;
    private DatabaseReference reference;
    private StorageReference storageReference;
    private ImageView icon;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        description = root.findViewById(R.id.site_description);
        emailAdd = root.findViewById(R.id.site_email);
        addressO = root.findViewById(R.id.site_address);
        phoneNumber = root.findViewById(R.id.site_number);
        popupText = root.findViewById(R.id.popup_text);
        emailApiKey = root.findViewById(R.id.email_api_key);

        updateDescription = root.findViewById(R.id.update_description);
        updateEmail = root.findViewById(R.id.update_email);
        updateAddress = root.findViewById(R.id.update_address);
        updateNumber = root.findViewById(R.id.update_number);
        updatePopup = root.findViewById(R.id.update_popup_text);
        updateIcon = root.findViewById(R.id.icon_change_site);
        updateEmailApi = root.findViewById(R.id.update_email_api_key);

        icon = root.findViewById(R.id.image_site_icon);

        reference = FirebaseDatabase.getInstance().getReference().child("basicinfo");
        storageReference = FirebaseStorage.getInstance().getReference();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                description.setText(String.valueOf(snapshot.child("description").getValue()));
                emailAdd.setText(String.valueOf(snapshot.child("email").getValue()));
                addressO.setText(String.valueOf(snapshot.child("address").getValue()));
                phoneNumber.setText(String.valueOf(snapshot.child("phone").getValue()));
                popupText.setText(String.valueOf(snapshot.child("popup").getValue()));
                emailApiKey.setText(String.valueOf(snapshot.child("emailkey").getValue()));
                Glide.with(getActivity()).load(snapshot.child("icon").getValue().toString()).into(icon);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
        updateDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String description1 = description.getText().toString();
                reference.child("description").setValue(description1).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Snackbar.make(v,"Description Updated Successfully", BaseTransientBottomBar.LENGTH_SHORT).show();
                    }
                });
            }
        });
        updateEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailAdd.getText().toString();
                reference.child("email").setValue(email).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Snackbar.make(v,"Email Updated Successfully",BaseTransientBottomBar.LENGTH_SHORT).show();
                    }
                });
            }
        });
        updateAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = addressO.getText().toString();
                reference.child("address").setValue(address).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Snackbar.make(v,"Address Updated S uccessfully",BaseTransientBottomBar.LENGTH_SHORT).show();
                    }
                });
            }
        });
        updateNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = phoneNumber.getText().toString();
                reference.child("phone").setValue(number).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Snackbar.make(v,"Phone Number Updated Successfully",BaseTransientBottomBar.LENGTH_SHORT).show();
                    }
                });
            }
        });
        updatePopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pop = popupText.getText().toString();
                reference.child("popup").setValue(pop).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Snackbar.make(view, "Popup Text Updated Successfully",BaseTransientBottomBar.LENGTH_SHORT).show();
                    }
                });
            }
        });
        updateEmailApi.setOnClickListener((v)->{
            reference.child("emailkey").setValue(emailApiKey.getText().toString()).addOnSuccessListener((s)->{
                Toast.makeText(getActivity(), "Email API Key updated", Toast.LENGTH_SHORT).show();
            });
        });
        updateIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent,"Choose and icon"),100);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 100 && resultCode == -1 && data.getData() != null){
            uploadIcon(data.getData());
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void uploadIcon(Uri data) {
        new Thread(new UploadIconThread(data)).start();
    }
    class UploadIconThread implements Runnable{
        Uri data;
        public UploadIconThread(Uri data) {
            this.data = data;
        }

        @Override
        public void run() {
            storageReference.child("basic_info/"+new Date().getTime()).putFile(data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(getActivity(), "Icon Uploaded", Toast.LENGTH_SHORT).show();
                    Task<Uri> task = taskSnapshot.getStorage().getDownloadUrl();
                    while (!task.isComplete());
                    Uri url = task.getResult();
                    reference.child("icon").setValue(url.toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(getActivity(), "Icon Updated", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
    }
}