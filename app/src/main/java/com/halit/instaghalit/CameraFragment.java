package com.halit.instaghalit;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;


public class CameraFragment extends Fragment {

    Button upload_btn,capture_btn;
    ImageView captured_iv;
    Uri mImageUri;
    final int CAPTURE_IMAGE = 1,GALLARY_PICK = 2;
    Bitmap bitmap;


    public CameraFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_camera, container, false);

        upload_btn = view.findViewById(R.id.upload_btn);
        capture_btn =  view.findViewById(R.id.capture_btn);
        captured_iv =  view.findViewById(R.id.captured_iv);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        capture_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                capturePhoto();

            }
        });

        upload_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storyAndImageTitle();

            }
        });


    }

    private void capturePhoto() {

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        String imageName = "image.jpg";
        mImageUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(),imageName));
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);
        startActivityForResult(cameraIntent, CAPTURE_IMAGE);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);



        if(requestCode == CAPTURE_IMAGE && resultCode == RESULT_OK){

            if(mImageUri != null) {
                Log.i("imageuri",mImageUri.getPath());
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(),mImageUri);
                    if(bitmap != null){
                        captured_iv.setImageBitmap(bitmap);
                    }
                    Log.i("bitmap",bitmap.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }


    }

    private void storyAndImageTitle() {

    }
}
