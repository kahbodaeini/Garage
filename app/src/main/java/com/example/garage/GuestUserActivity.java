package com.example.garage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

        ArrayList<Car> cars = new ArrayList<>();
        cars = SignAndLog.currentUser.getCars();

        if(!cars.isEmpty()){
            //TODO show the user's cars
        }
    }
}
