package com.example.garage;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.UserHandle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Controller.SignAndLog;
import Model.Car;

public class CarsFragment extends Fragment {

    public CarsFragment() {
        // Required empty public constructor
    }

    View rootView;
    ArrayList<Car> allCars;
    LinearLayout mainLayout;
    LinearLayout barLayout;
    ScrollView scrollView;
    LinearLayout car_layout;
    int status;
    final boolean[] firstTime = {true};
    private static int cores = Runtime.getRuntime().availableProcessors();
    private static ExecutorService executor = Executors.newFixedThreadPool(cores + 1);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_cars, container, false);

        if (SignAndLog.currentUser != null)
            allCars = SignAndLog.currentUser.getCars();
        double totalPrice = 0;

        if(!allCars.isEmpty())
            for(int i = 0; i < allCars.size(); i++)
                totalPrice += allCars.get(i).calculatePrice();

        TextView totalPriceTextView = rootView.findViewById(R.id.total_prices);
        totalPriceTextView.setText("Total Prices Of your car: " + totalPrice);

        barLayout = rootView.findViewById(R.id.bar);
        mainLayout = rootView.findViewById(R.id.main_linear_layout);
        scrollView = rootView.findViewById(R.id.scroll_view);
        car_layout = rootView.findViewById(R.id.status_layout);
        Button addCar = rootView.findViewById(R.id.add_car);
        addCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AddCarActivity.class));
            }
        });
        loadCars();

        return rootView;
    }

    private synchronized void loadCars() {
        final ImageButton carImage = rootView.findViewById(R.id.car_image);
        final TextView carCompany = rootView.findViewById(R.id.car_company);
        final TextView carSign = rootView.findViewById(R.id.car_sign);
        final TextView carType = rootView.findViewById(R.id.car_type);
        final TextView carColor = rootView.findViewById(R.id.car_color);

        int limit = allCars.size();
        executor.execute(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < limit; i++) {
                    Car car = allCars.get(i);
                    final String logo = CarActivity.getLogoImagePath(car);

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (firstTime[0]) {
                                carSign.setText("Sign: " + car.getSign());
                                carCompany.setText("Company: " + car.getCompany());
                                carType.setText("Type: " + car.getType());
                                carColor.setText("Color: " + car.getColor());
                                car_layout.setVisibility(View.VISIBLE);
                                Glide.with(getActivity())
                                        .load(logo)
                                        .into(carImage);
                                carImage.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        SignAndLog.currentCar = car;
                                        startActivity(new Intent(getActivity(), CarActivity.class));
                                    }
                                });
                                firstTime[0] = false;
                            } else {
                                LayoutInflater vi = (LayoutInflater) getActivity()
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

                                ((TextView) linearLayout.findViewById(R.id.car_sign)).setText("Sign: " + car.getSign());
                                ((TextView) linearLayout.findViewById(R.id.car_company)).setText("Company: " + car.getCompany());
                                ((TextView) linearLayout.findViewById(R.id.car_type)).setText("Type: " + car.getType());
                                ((TextView) linearLayout.findViewById(R.id.car_color)).setText("Color: " + car.getColor());
                                Glide.with(getActivity())
                                        .load(logo)
                                        .into(((ImageButton) linearLayout.findViewById(R.id.car_image)));
                                ((ImageButton) linearLayout.findViewById(R.id.car_image)).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        SignAndLog.currentCar = car;
                                        startActivity(new Intent(getActivity(), CarActivity.class));
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

}