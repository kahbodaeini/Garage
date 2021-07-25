package com.example.garage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.os.Environment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.fragment.app.Fragment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.util.zip.Inflater;

import Controller.SignAndLog;

public class ProfileFragment extends Fragment {

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        rootView.findViewById(R.id.add_budget).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buyHeadLightPopupWindow(view, inflater);
            }
        });
        rootView.findViewById(R.id.change_pic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    addPic(rootView.findViewById(R.id.imageView));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        return rootView;
    }

    public void addPic(ImageView imageView) throws IOException {
        loadImage(imageView);
    }

    public void loadImage(ImageView imageView) throws IOException {
        String path = "/sdcard/Images/test_image.jpg";
        File imgFile = new  File(path);
        if(imgFile.exists()){
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            imageView.setImageBitmap(myBitmap);
            saveImage(imgFile);
        }
    }

    public void saveImage(File file) throws IOException {
        String directory = "";
        File newFile = new File(directory, file.getName());
        FileChannel outputChannel = null;
        FileChannel inputChannel = null;
        try {
            outputChannel = new FileOutputStream(newFile).getChannel();
            inputChannel = new FileInputStream(file).getChannel();
            inputChannel.transferTo(0, inputChannel.size(), outputChannel);
            inputChannel.close();
        } finally {
            if (inputChannel != null) inputChannel.close();
            if (outputChannel != null) outputChannel.close();
        }
    }

    public void buyHeadLightPopupWindow(View view, LayoutInflater serviceInflater) {
        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater) serviceInflater;
        View popupView = inflater.inflate(R.layout.popup_window, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        // dismiss the popup window when touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }

}