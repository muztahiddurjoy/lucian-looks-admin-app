package com.epizy.muztahiddurjoy.lucianlooksadminapp.ui.gallery;

import android.app.ProgressDialog;
import android.content.Intent;
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
import com.epizy.muztahiddurjoy.lucianlooksadminapp.databinding.FragmentGalleryBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
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

import static android.app.Activity.RESULT_OK;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private FragmentGalleryBinding binding;


    private ImageView imageOne, imageTwo, imageThree, imageFour;
    private DatabaseReference reference;
    private StorageReference storageReferencer;
    private Button uploadOne, uploadTwo, uploadThree, uploadFour;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        imageOne = root.findViewById(R.id.carousel_image_one);
        imageTwo = root.findViewById(R.id.carousel_image_two);
        imageThree = root.findViewById(R.id.carousel_image_three);
        imageFour = root.findViewById(R.id.carousel_image_four);

        uploadOne = root.findViewById(R.id.upload_carousel_one_image);
        uploadTwo = root.findViewById(R.id.upload_carousel_two_image);
        uploadThree = root.findViewById(R.id.upload_carousel_three_image);
        uploadFour = root.findViewById(R.id.upload_carousel_four_image);

        reference = FirebaseDatabase.getInstance().getReference().child("carousel");
        storageReferencer = FirebaseStorage.getInstance().getReference();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                Glide.with(getActivity()).load(snapshot.child("0").getValue().toString()).into(imageOne);
                Glide.with(getActivity()).load(snapshot.child("1").getValue().toString()).into(imageTwo);
                Glide.with(getActivity()).load(snapshot.child("2").getValue().toString()).into(imageThree);
                Glide.with(getActivity()).load(snapshot.child("3").getValue().toString()).into(imageFour);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
        uploadOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImageOne();
            }
        });
        uploadTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImageTwo();
            }
        });
        uploadThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImageThree();
            }
        });
        uploadFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImageFour();
            }
        });
        return root;
    }

    private void uploadImageFour() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select an image"),4);
    }

    private void uploadImageThree() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select an image"),3);
    }

    private void uploadImageTwo() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select an image"),2);
    }

    private void uploadImageOne() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Sleect an image"),1);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data.getData() != null){
            uploadFirebaseOne(data.getData());
        }
        if (requestCode == 2 && resultCode == RESULT_OK && data.getData() !=null){
            uploadFirebaseTwo(data.getData());
        }
        if (requestCode == 3 && resultCode == RESULT_OK && data.getData() != null){
            uploadFirebaseThree(data.getData());
        }
        if (requestCode == 4 && resultCode == RESULT_OK && data.getData() != null){
            uploadFirebaseFour(data.getData());
        }
    }

    private void uploadFirebaseOne(Uri data) {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Uploading..");
        progressDialog.show();
        storageReferencer.child("carousel/"+new Date().getTime()).putFile(data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> task = taskSnapshot.getStorage().getDownloadUrl();
                while (!task.isComplete());
                Uri url = task.getResult();
                reference.child("0").setValue(url.toString());
                progressDialog.dismiss();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                int val = (int) snapshot.getBytesTransferred();
                progressDialog.setMessage("Transferred: "+val);
            }
        });
    }

    private void uploadFirebaseTwo(Uri data) {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Uploading..");
        progressDialog.show();
        storageReferencer.child("carousel/"+new Date().getTime()).putFile(data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> task = taskSnapshot.getStorage().getDownloadUrl();
                while (!task.isComplete());
                Uri url = task.getResult();
                reference.child("1").setValue(url.toString());
                progressDialog.dismiss();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                int val = (int) snapshot.getBytesTransferred();
                progressDialog.setMessage("Transferred: "+val);
            }
        });
    }

    private void uploadFirebaseThree(Uri data) {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Uploading..");
        progressDialog.show();
        storageReferencer.child("carousel/"+new Date().getTime()).putFile(data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> task = taskSnapshot.getStorage().getDownloadUrl();
                while (!task.isComplete());
                Uri url = task.getResult();
                reference.child("2").setValue(url.toString());
                progressDialog.dismiss();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                int val = (int) snapshot.getBytesTransferred();
                progressDialog.setMessage("Transferred: "+val);
            }
        });
    }

    private void uploadFirebaseFour(Uri data) {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Uploading..");
        progressDialog.show();
        storageReferencer.child("carousel/"+new Date().getTime()).putFile(data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> task = taskSnapshot.getStorage().getDownloadUrl();
                while (!task.isComplete());
                Uri url = task.getResult();
                reference.child("3").setValue(url.toString());
                progressDialog.dismiss();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                int val = (int) snapshot.getBytesTransferred();
                progressDialog.setMessage("Transferred: "+val);
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}