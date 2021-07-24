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
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Model.Car;

public class AllCarsActivity extends AppCompatActivity {
    final boolean[] firstTime = {true};
    ImageButton backButton;
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
        setContentView(R.layout.all_cars);

        barLayout = findViewById(R.id.bar);
        mainLayout = findViewById(R.id.main_layout);
        scrollView = findViewById(R.id.scroll_view);
        car_layout = findViewById(R.id.car_layouts);
        backButton = findViewById(R.id.back_image_button);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AllCarsActivity.this, GuestUserActivity.class));
            }
        });
        loadCars();
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    private synchronized void loadCars() {

        final ImageButton carImage = findViewById(R.id.car_image);
        final TextView carName = findViewById(R.id.car_name);
        final TextView carPrice = findViewById(R.id.car_price);
        final TextView car_model = findViewById(R.id.car_model);

        ArrayList<Car> allCars = Car.getAllCars();
        int limit = allCars.size();
        executor.execute(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < limit; i++) {
                    Car car = allCars.get(i);
                    final String logo = CarActivity.getLogoImagePath(car);

                    AllCarsActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (firstTime[0]) {
                                carPrice.setText("Price: " + car.calculatePrice());
                                carName.setText("Company: " + car.getCompany());
                                car_model.setText("Type: " + car.getType());
                                car_layout.setVisibility(View.VISIBLE);
                                Glide.with(AllCarsActivity.this)
                                        .load(logo)
                                        .into(carImage);
                                carImage.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
//                                        setSymbol(symbol);
                                        startActivity(new Intent(AllCarsActivity.this, CarActivity.class));
                                    }
                                });
                                firstTime[0] = false;
                            } else {
                                LayoutInflater vi = (LayoutInflater) AllCarsActivity.this
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

                                ((TextView) linearLayout.findViewById(R.id.car_price)).setText("Price: " + car.calculatePrice());
                                ((TextView) linearLayout.findViewById(R.id.car_name)).setText("Company: " + car.getCompany());
                                ((TextView) linearLayout.findViewById(R.id.car_type)).setText("Type: " + car.getType());
                                Glide.with(AllCarsActivity.this)
                                        .load(logo)
                                        .into(((ImageButton) linearLayout.findViewById(R.id.car_image)));
                                ((ImageButton) linearLayout.findViewById(R.id.car_image)).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
//                                        setSymbol(symbol);
                                        startActivity(new Intent(AllCarsActivity.this, CarActivity.class));
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
