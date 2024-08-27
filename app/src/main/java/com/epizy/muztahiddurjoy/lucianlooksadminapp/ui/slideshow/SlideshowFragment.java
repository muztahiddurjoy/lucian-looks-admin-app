package com.epizy.muztahiddurjoy.lucianlooksadminapp.ui.slideshow;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.epizy.muztahiddurjoy.lucianlooksadminapp.Dataset.CTADataset;
import com.epizy.muztahiddurjoy.lucianlooksadminapp.R;
import com.epizy.muztahiddurjoy.lucianlooksadminapp.databinding.FragmentSlideshowBinding;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    private FragmentSlideshowBinding binding;


    private TextInputEditText cta_titleOne, cta_desOne, cta_titleTwo, cta_desTwo, cta_titleThree, cta_desTheee, cta_btnTextOne, cta_btnTextTwo, cta_btnTextThree;
    private ImageView cta_one_image, cta_two_image,cta_three_image;
    private Button cta_image_update_one,cta_image_update_two, cta_image_update_three, total_update_cta_one,total_update_cta_two ,total_update_cta_three ;
    private Spinner spinner_one, spinner_two, spinner_three;
    private List<String> target_array = new ArrayList<>();
    private DatabaseReference reference;
    private StorageReference storageReference;
    private String target_one_string;
    private String target_two_string;
    private String target_three_string;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);
        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        cta_titleOne = root.findViewById(R.id.title_cta_one);
        cta_titleTwo = root.findViewById(R.id.title_cta_two);
        cta_titleThree = root.findViewById(R.id.title_cta_three);

        cta_desOne = root.findViewById(R.id.desc_cta_one);
        cta_desTwo = root.findViewById(R.id.desc_cta_two);
        cta_desTheee = root.findViewById(R.id.desc_cta_three);


        cta_one_image = root.findViewById(R.id.image_cta_one);
        cta_two_image = root.findViewById(R.id.image_cta_two);
        cta_three_image = root.findViewById(R.id.image_cta_three);

        spinner_one = root.findViewById(R.id.spinner_cta_one);
        spinner_two = root.findViewById(R.id.spinner_cta_two);
        spinner_three = root.findViewById(R.id.spinner_cta_three);

        cta_image_update_one = root.findViewById(R.id.update_image_cta_one);
        cta_image_update_two = root.findViewById(R.id.update_image_cta_two);
        cta_image_update_three = root.findViewById(R.id.update_image_cta_three);

        cta_btnTextOne = root.findViewById(R.id.btn_text_one);
        cta_btnTextTwo = root.findViewById(R.id.btn_text_two);
        cta_btnTextThree = root.findViewById(R.id.btn_text_three);

        total_update_cta_one = root.findViewById(R.id.update_cta_one);
        total_update_cta_two = root.findViewById(R.id.update_cta_two);
        total_update_cta_three = root.findViewById(R.id.update_cta_three);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),R.array.option_names, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_one.setAdapter(adapter);
        spinner_two.setAdapter(adapter);
        spinner_three.setAdapter(adapter);

        target_array.addAll(Arrays.asList("shop","services"));
        reference = FirebaseDatabase.getInstance().getReference().child("topcta");
        storageReference = FirebaseStorage.getInstance().getReference();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                CTADataset data = snapshot.child("0").getValue(CTADataset.class);
                CTADataset dataTw = snapshot.child("1").getValue(CTADataset.class);
                CTADataset dataThr = snapshot.child("2").getValue(CTADataset.class);
                cta_titleOne.setText(data.getName());
                cta_desOne.setText(data.getDescription());
                cta_btnTextOne.setText(data.getBtntext());

                cta_titleTwo.setText(dataTw.getName());
                cta_desTwo.setText(dataTw.getDescription());
                cta_btnTextTwo.setText(dataTw.getBtntext());

                cta_titleThree.setText(dataThr.getName());
                cta_desTheee.setText(dataThr.getDescription());
                cta_btnTextThree.setText(dataThr.getBtntext());

                Glide.with(getActivity()).load(data.getImageurl()).into(cta_one_image);
                Glide.with(getActivity()).load(dataTw.getImageurl()).into(cta_two_image);
                Glide.with(getActivity()).load(dataThr.getImageurl()).into(cta_three_image);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        spinner_one.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                target_one_string = (String) adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner_two.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                target_two_string = (String) adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner_three.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                target_three_string = (String) adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        cta_image_update_one.setOnClickListener((v)->{
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(intent,"Choose an image"),1000);
        });

        cta_image_update_two.setOnClickListener((v)->{
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(intent,"Choose an image"),2000);
        });

        cta_image_update_three.setOnClickListener((v)->{
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(intent,"Choose an image"),3000);
        });

        total_update_cta_one.setOnClickListener((v)->{
            reference.child("0").child("name").setValue(cta_titleOne.getText().toString());
            reference.child("0").child("description").setValue(cta_desOne.getText().toString());
            reference.child("0").child("link").setValue("/"+target_one_string);
            reference.child("0").child("btntext").setValue(cta_btnTextOne.getText().toString());
            });
        total_update_cta_two.setOnClickListener((v)->{
            reference.child("1").child("name").setValue(cta_titleTwo.getText().toString());
            reference.child("1").child("description").setValue(cta_desTwo.getText().toString());
            reference.child("1").child("link").setValue("/"+target_two_string);
            reference.child("1").child("btntext").setValue(cta_btnTextTwo.getText().toString());
        });
        total_update_cta_three.setOnClickListener((v)->{
            reference.child("2").child("name").setValue(cta_titleThree.getText().toString());
            reference.child("2").child("description").setValue(cta_desTheee.getText().toString());
            reference.child("2").child("link").setValue("/"+target_three_string);
            reference.child("2").child("btntext").setValue(cta_btnTextThree.getText().toString());
        });

        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000 && resultCode == Activity.RESULT_OK && data.getData() != null){
                final ProgressDialog dialog = new ProgressDialog(getActivity());
                dialog.setTitle("Uploading..");
                dialog.show();
                storageReference.child("cta_image/"+ new Date().getTime()).putFile(data.getData()).addOnProgressListener((snp)->{
                    int val = (int) snp.getBytesTransferred();
                    dialog.setMessage("Transferred: "+val);
                }).addOnSuccessListener((s)->{
                    dialog.dismiss();
                    Task<Uri> task = s.getStorage().getDownloadUrl();
                    while (!task.isComplete());
                    reference.child("0").child("imageurl").setValue(task.getResult());
                });
        }
        if (requestCode == 2000 && resultCode == Activity.RESULT_OK && data.getData() != null){
            final ProgressDialog dialog = new ProgressDialog(getActivity());
            dialog.setTitle("Uploading..");
            dialog.show();
            storageReference.child("cta_image/"+ new Date().getTime()).putFile(data.getData()).addOnProgressListener((snp)->{
                int val = (int) snp.getBytesTransferred();
                dialog.setMessage("Transferred: "+val);
            }).addOnSuccessListener((s)->{
                dialog.dismiss();
                Task<Uri> task = s.getStorage().getDownloadUrl();
                while (!task.isComplete());
                reference.child("1").child("imageurl").setValue(task.getResult());
            });
        }
        if (requestCode == 3000 && resultCode == Activity.RESULT_OK && data.getData() != null){
            final ProgressDialog dialog = new ProgressDialog(getActivity());
            dialog.setTitle("Uploading..");
            dialog.show();
            storageReference.child("cta_image/"+ new Date().getTime()).putFile(data.getData()).addOnProgressListener((snp)->{
                int val = (int) snp.getBytesTransferred();
                dialog.setMessage("Transferred: "+val);
            }).addOnSuccessListener((s)->{
                dialog.dismiss();
                Task<Uri> task = s.getStorage().getDownloadUrl();
                while (!task.isComplete());
                reference.child("2").child("imageurl").setValue(task.getResult());
            });
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}