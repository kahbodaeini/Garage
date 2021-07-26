package com.example.garage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import org.json.JSONException;

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
import Model.User;

public class ProfileFragment extends Fragment {

    public ProfileFragment() {
        // Required empty public constructor
    }

    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        TextView personUsername = rootView.findViewById(R.id.username);
        personUsername.setText(SignAndLog.currentUser.getUserName());

        TextView personBudget = rootView.findViewById(R.id.budget);
        personBudget.setText(String.valueOf(SignAndLog.currentUser.getBudget()));

        TextView personAbout = rootView.findViewById(R.id.about);
        personAbout.setText(SignAndLog.currentUser.getAbout());

        rootView.findViewById(R.id.change_pic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View popupView = inflater.inflate(R.layout.popup_window_repairment, null);

                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true;
                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

                EditText editText = popupView.findViewById(R.id.percent);
                editText.setHint("Input the path of the image in your device.");

                Button done = popupView.findViewById(R.id.done);
                TextView error = popupView.findViewById(R.id.error);
                done.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (editText.getText().toString().isEmpty()) {
                            error.setText("Please Fill All The Text Box!");
                        } else {
                            try {
                                if (!addPic(rootView.findViewById(R.id.imageView), editText.getText().toString())) {
                                    error.setText("Please Fill The Text Box correctly!");
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                });

                popupView.findViewById(R.id.back_image_button).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupWindow.dismiss();
                    }
                });
            }
        });

        rootView.findViewById(R.id.add_budget).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View popupView = inflater.inflate(R.layout.popup_window_repairment, null);

                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true;
                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

                EditText editText = popupView.findViewById(R.id.percent);
                editText.setHint("How Much You Want To Add To Your Budget?");

                Button done = popupView.findViewById(R.id.done);
                TextView error = popupView.findViewById(R.id.error);
                done.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (editText.getText().toString().isEmpty()) {
                            error.setText("Please Fill All The Text Box!");
                        } else {
                            try {
                                double budget = Double.parseDouble(String.valueOf(editText.getText()));
                                SignAndLog.currentUser.setBudget(SignAndLog.currentUser.getBudget() +
                                        budget);
                            } catch (NumberFormatException | IOException ex) {
                                error.setText("you should enter a valid number for budget!");
                            }
                        }
                    }
                });

                popupView.findViewById(R.id.back_image_button).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupWindow.dismiss();
                    }
                });
            }
        });


        rootView.findViewById(R.id.change_username).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View popupView = inflater.inflate(R.layout.popup_window_change_username, null);

                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true;
                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

                EditText currentUsernameEdittext = popupView.findViewById(R.id.current_username);
                EditText currentPasswordEdittext = popupView.findViewById(R.id.current_password_username_change);
                EditText newUsernameEdittext = popupView.findViewById(R.id.new_username);


                Button done = popupView.findViewById(R.id.done);
                TextView error = popupView.findViewById(R.id.error);

                done.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String currentUsername = "";
                        String currentPassword = "";
                        String newUsername = "";
                        currentUsername = String.valueOf(currentUsernameEdittext.getText());
                        currentPassword = String.valueOf(currentPasswordEdittext.getText());
                        newUsername = String.valueOf(newUsernameEdittext.getText());
                        if (!currentPassword.equals("") && !currentUsername.equals("") && !newUsername.equals("")) {
                            if (!currentUsername.equals(SignAndLog.currentUser.getUserName())) {
                                error.setText("There's No User With This Username!");
                            } else {
                                if (currentPassword.equals(SignAndLog.currentUser.getPassword())) {
                                    try {
                                        SignAndLog.currentUser.setUserName(newUsername);
                                        error.setText("Your Username Successfully Changed To" + newUsername + "!");
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    error.setText("Wrong Password!");
                                }
                            }
                        } else {
                            error.setText("Please Fill All The Text Boxes!");
                        }
                    }
                });

                popupView.findViewById(R.id.back_image_button).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupWindow.dismiss();
                    }
                });
            }
        });

        rootView.findViewById(R.id.add_about).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View popupView = inflater.inflate(R.layout.popup_window_repairment, null);

                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true;
                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

                EditText editText = popupView.findViewById(R.id.percent);
                editText.setHint("Description");

                Button done = popupView.findViewById(R.id.done);
                TextView error = popupView.findViewById(R.id.error);

                done.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (editText.getText().toString().isEmpty()) {
                            error.setText("Please Fill All The Text Boxes!");
                        } else {
                            try {
                                SignAndLog.currentUser.setAbout(String.valueOf(editText.getText()));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

                popupView.findViewById(R.id.back_image_button).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupWindow.dismiss();
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

                EditText currentPasswordEdittext = popupView.findViewById(R.id.current_password);
                EditText newPasswordEdittext = popupView.findViewById(R.id.new_password);


                Button done = popupView.findViewById(R.id.done);
                TextView error = popupView.findViewById(R.id.error);

                done.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String currentPassword = "";
                        String newPassword = "";
                        currentPassword = String.valueOf(currentPasswordEdittext.getText());
                        newPassword = String.valueOf(newPasswordEdittext.getText());
                        if (!currentPassword.equals("") && !newPassword.equals("")) {
                            try {
                                if (SignAndLog.currentUser.changePassword(currentPassword, newPassword)) {
                                    error.setText("Your Password Successfully Changed!");
                                } else {
                                    error.setText("Wrong Password!");
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            error.setText("Please Fill All The Text Boxes!");
                        }
                    }
                });

                popupView.findViewById(R.id.back_image_button).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupWindow.dismiss();
                    }
                });
            }
        });

        return rootView;
    }

    public boolean addPic(ImageView imageView, String path) throws IOException {
        File imgFile = new File(path);
        if (imgFile.exists()) {
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            imageView.setImageBitmap(myBitmap);
            SignAndLog.currentUser.setImagePath(path);
//            saveImage(imgFile);
            return true;
        }
        return false;
    }

//    public void saveImage(File file) throws IOException {
//        String directory = "";
//        File newFile = new File(directory, file.getName());
//        FileChannel outputChannel = null;
//        FileChannel inputChannel = null;
//        try {
//            outputChannel = new FileOutputStream(newFile).getChannel();
//            inputChannel = new FileInputStream(file).getChannel();
//            inputChannel.transferTo(0, inputChannel.size(), outputChannel);
//            inputChannel.close();
//        } finally {
//            if (inputChannel != null) inputChannel.close();
//            if (outputChannel != null) outputChannel.close();
//        }
//    }

//    public void refresh(){
//        finish();
//        startActivity(getIntent());
//    }

}