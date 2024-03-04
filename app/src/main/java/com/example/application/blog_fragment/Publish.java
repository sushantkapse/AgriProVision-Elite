package com.example.application.blog_fragment;
import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.provider.Settings;
import android.content.Intent;
import android.os.Bundle;
import static android.app.Activity.RESULT_OK;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.application.Model;
import com.example.application.R;
import com.example.application.databinding.FragmentProfileBinding;
import com.example.application.databinding.FragmentPublishBinding;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Date;
import android.graphics.Bitmap;

import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.Toast;
import java.util.Date;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;


;



public class Publish extends Fragment {

    FragmentPublishBinding fragmentPublishBinding;
    Uri filepath;
    private Bitmap imageToStore;
    Database db; // Initialize your Database object
//    ImageView imageView ;


    public Publish() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentPublishBinding = FragmentPublishBinding.inflate(inflater, container, false);
        db = new Database(getContext()); // Initialize your Database object
        return fragmentPublishBinding.getRoot();

//        imageView = (ImageView) fragmentPublishBinding.publishImg.
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        selectImage();
        uploadData();
    }

    private void selectImage() {
        fragmentPublishBinding.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Your Image"), 99);
//                startActivityForResult(Intent.createChooser(intent, "Select Your Image"), 101);
            }
        });
    }

    private void uploadData() {
        fragmentPublishBinding.btnPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                String x = getPath();
                if (filepath != null) {
                    String title = fragmentPublishBinding.publishTittle.getText().toString();
                    String desc = fragmentPublishBinding.publishDesc.getText().toString();
                    String author = fragmentPublishBinding.publishAuthor.getText().toString();
                    String date = (String) DateFormat.format("dd", new Date());
                    String month = (String) DateFormat.format("MMM", new Date());
                    String final_date = date + " " + month;
                    int share_count = 0;
                    int u_id = 1;
//

                    boolean res = db.insertBlogData(new Model(u_id,title,desc,author,date,imageToStore,share_count));
//                    boolean res = db.insertBlogData(u_id,title, desc, author, final_date, share_count,imageBytes);
                    if (res) {
                        Toast.makeText(getContext(), "Details saved successfully", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Please select an image first", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try{
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == 99 && resultCode == RESULT_OK && data != null && data.getData() != null) {
                filepath = data.getData();
                imageToStore = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(),filepath);
                fragmentPublishBinding.publishImg.setVisibility(View.VISIBLE);
                fragmentPublishBinding.publishImg.setImageURI(filepath);
                fragmentPublishBinding.view.setVisibility(View.INVISIBLE);
                fragmentPublishBinding.publishSelectImage.setVisibility(View.INVISIBLE);


            }




         }

        catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }



    private byte[] convertImageToByteArray(Uri filepath) {
        try {
            InputStream inputStream = getActivity().getContentResolver().openInputStream(filepath);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
