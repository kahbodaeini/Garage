package com.example.garage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.os.Environment;
import android.view.Gravity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

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

import java.io.IOException;

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

        rootView.findViewById(R.id.add_budget).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                View popupView = inflater.inflate(R.layout.popup_window_repairment, null);

                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true;
                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

                EditText editText = rootView.findViewById(R.id.percent);
                editText.setHint("How Much You Want To Add To Your Budget?");
                try {
                    SignAndLog.currentUser.setBudget(SignAndLog.currentUser.getBudget() +
                            Double.parseDouble(String.valueOf(editText.getText())));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                popupView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        popupWindow.dismiss();
                        return true;
                    }
                });
            }
        });


        rootView.findViewById(R.id.change_username).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                View popupView = inflater.inflate(R.layout.popup_window_change_username, null);

                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true;
                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

                EditText currentUsernameEdittext = rootView.findViewById(R.id.current_username);
                EditText currentPasswordEdittext = rootView.findViewById(R.id.current_password_username_change);
                EditText newUsernameEdittext = rootView.findViewById(R.id.new_username);

                String currentUsername = "";
                String currentPassword = "";
                String newUsername = "";

                currentUsername = String.valueOf(currentUsernameEdittext.getText());
                currentPassword = String.valueOf(currentPasswordEdittext.getText());
                newUsername = String.valueOf(newUsernameEdittext.getText());

                if (!currentPassword.equals("") && !currentUsername.equals("") && !newUsername.equals("")) {
                    if (!currentUsername.equals(SignAndLog.currentUser.getUserName())) {
                        TextView error = rootView.findViewById(R.id.error);
                        error.setText("There's No User With This Username!");
                    } else {
                        if (currentPassword.equals(SignAndLog.currentUser.getPassword())) {
                            try {
                                SignAndLog.currentUser.setUserName(newUsername);
                                TextView error = rootView.findViewById(R.id.error);
                                error.setText("Your Username Successfully Changed To" + newUsername + "!");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            TextView error = rootView.findViewById(R.id.error);
                            error.setText("Wrong Password!");
                        }
                    }
                }
                else{
                    TextView error = rootView.findViewById(R.id.error);
                    error.setText("Please Fill All The Text Boxes!");
                }

                popupView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        popupWindow.dismiss();
                        return true;
                    }
                });
            }
        });

        rootView.findViewById(R.id.add_about).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View popupView = inflater.inflate(R.layout.popup_window_change_username, null);

                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true;
                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

                EditText editText = rootView.findViewById(R.id.percent);
                editText.setHint("Description");
                try {
                    SignAndLog.currentUser.setAbout(String.valueOf(editText.getText()));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                popupView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        popupWindow.dismiss();
                        return true;
                    }
                });
            }
        });

        rootView.findViewById(R.id.change_password).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View popupView = inflater.inflate(R.layout.popup_window_change_password, null);

                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true;
                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

                EditText currentPasswordEdittext = rootView.findViewById(R.id.current_password);
                EditText newPasswordEdittext = rootView.findViewById(R.id.new_password);

                String currentPassword = "";
                String newPassword = "";

                currentPassword = String.valueOf(currentPasswordEdittext.getText());
                newPassword = String.valueOf(newPasswordEdittext.getText());

                if(!currentPassword.equals("") && !newPassword.equals("")){
                    try {
                        if(SignAndLog.currentUser.changePassword(currentPassword, newPassword)){
                            TextView error = rootView.findViewById(R.id.error);
                            error.setText("Your Password Successfully Changed!");
                        }
                        else{
                            TextView error = rootView.findViewById(R.id.error);
                            error.setText("Wrong Password!");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    TextView error = rootView.findViewById(R.id.error);
                    error.setText("Please Fill All The Text Boxes!");
                }

                popupView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        popupWindow.dismiss();
                        return true;
                    }
                });
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



}