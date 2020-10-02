package com.halit.instaghalit;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


public class CameraFragment extends Fragment {

    Button upload_btn,capture_btn;
    ImageView captured_iv;


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

    }

    private void storyAndImageTitle() {

    }
}
