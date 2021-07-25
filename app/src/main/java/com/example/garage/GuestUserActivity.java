package com.example.garage;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.garage.databinding.ActivityMainBinding;

import java.util.ArrayList;

import Controller.SignAndLog;
import Model.Car;

public class GuestUserActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guest_user_layout);

        ImageView back = findViewById(R.id.back_image_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GuestUserActivity.this, AllUsersActivity.class));
            }
        });


        TextView firstnameTextview = findViewById(R.id.first_name);
        firstnameTextview.setText(SignAndLog.currentUser.getFirstName());

        TextView lastnameTextview = findViewById(R.id.last_name);
        lastnameTextview.setText(SignAndLog.currentUser.getLastName());

        String about = SignAndLog.currentUser.getAbout();
        if (about != null){
            TextView aboutTextview = findViewById(R.id.car_guest_image);
            aboutTextview.setText(about);
        }

        ArrayList<Car> cars = SignAndLog.currentUser.getCars();

        if(!cars.isEmpty()){
            Car car1 = cars.get(0);
            if (car1 != null){
                TextView carInfo1 = findViewById(R.id.car1);
                Button color1 = findViewById(R.id.car_color1);
                carInfo1.setText("Company: " + car1.getCompany() + "\t" + "Type: " + car1.getType());
                color1.setBackgroundColor(Color.parseColor(car1.getColor().toString()));

            }

            Car car2 = cars.get(1);
            if (car2 != null){
                TextView carInfo2 = findViewById(R.id.car2);
                Button color2 = findViewById(R.id.car_color2);
                carInfo2.setText("Company: " + car2.getCompany() + "\t" + "Type: " + car2.getType());
                color2.setBackgroundColor(Color.parseColor(car2.getColor().toString()));
            }

            Car car3 = cars.get(2);
            if (car3 != null){
                TextView carInfo3 = findViewById(R.id.car3);
                Button color3 = findViewById(R.id.car_color3);
                carInfo3.setText("Company: " + car3.getCompany() + "\t" + "Type: " + car3.getType());
                color3.setBackgroundColor(Color.parseColor(car3.getColor().toString()));
            }
        }
    }
}
