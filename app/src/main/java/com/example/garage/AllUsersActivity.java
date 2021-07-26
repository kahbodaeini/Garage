package com.example.garage;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Controller.SignAndLog;
import Model.User;

public class AllUsersActivity extends AppCompatActivity {
    final boolean[] firstTime = {true};
    ImageView backButton;
    LinearLayout mainLayout;
    LinearLayout barLayout;

    private static int cores = Runtime.getRuntime().availableProcessors();
    private static ExecutorService executor = Executors.newFixedThreadPool(cores + 1);

    ScrollView scrollView;
    LinearLayout car_layout;
    int status;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_users);

        barLayout = findViewById(R.id.bar);
        mainLayout = findViewById(R.id.main_layout);
        scrollView = findViewById(R.id.scroll_view);
        car_layout = findViewById(R.id.car_layouts);
        backButton = findViewById(R.id.back_image_button);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AllUsersActivity.this, MainActivity.class));
            }
        });
        loadCars();
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    private synchronized void loadCars() {

        final TextView name = findViewById(R.id.name);
        final TextView userNum = findViewById(R.id.user_num);
        final TextView username = findViewById(R.id.username);

        ArrayList<User> users = User.getAllUsers();
        int limit = users.size();
        executor.execute(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < limit; i++) {
                    User user = users.get(i);

                    int finalI = i + 1;
                    AllUsersActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (firstTime[0]) {
                                name.setText("name: " + user.getFirstName() + " " + user.getLastName());
                                userNum.setText("   " + String.valueOf(finalI));
                                username.setText("username: " + user.getUserName());
                                car_layout.setVisibility(View.VISIBLE);
                                car_layout.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        SignAndLog.guestUser = user;
                                        startActivity(new Intent(AllUsersActivity.this, GuestUserActivity.class));
                                    }
                                });
                                firstTime[0] = false;
                            } else {
                                LayoutInflater vi = (LayoutInflater) AllUsersActivity.this
                                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                View v = vi.inflate(R.layout.car_layout, null);

                                final LinearLayout linearLayout = v.findViewById(R.id.car_layouts);

                                if (linearLayout.getParent() != null) {
                                    ((ViewGroup) linearLayout.getParent()).removeView(linearLayout); // <- fix
                                }
                                final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                                params.setMargins(0, 10, 0, 10);

                                params.gravity = Gravity.CENTER_VERTICAL;
                                mainLayout.setOrientation(LinearLayout.VERTICAL);

                                ((TextView) linearLayout.findViewById(R.id.user_num)).setText("   " + String.valueOf(finalI));
                                ((TextView) linearLayout.findViewById(R.id.name)).setText("name: " + user.getFirstName() + " " + user.getLastName());
                                ((TextView) linearLayout.findViewById(R.id.username)).setText("username: " + user.getUserName());
                                (linearLayout).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
//                                        setSymbol(symbol);
                                        startActivity(new Intent(AllUsersActivity.this, GuestUserActivity.class));
                                    }
                                });
                                linearLayout.setVisibility(View.VISIBLE);
                                if (status == 2) {
                                    linearLayout.setBackgroundColor(Color.GREEN);
                                }
                                mainLayout.addView(linearLayout, params);
                            }
                            car_layout.setVisibility(View.VISIBLE);
                        }
                    });
                }

            }
        });

    }

    private void setSymbol(String symbol){

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
