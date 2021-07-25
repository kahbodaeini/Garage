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

import Model.Car;
import Model.User;

public class GuestUserActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private final User user;

    public GuestUserActivity(User user){
        this.user = user;
    }
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
        firstnameTextview.setText(user.getFirstName());

        TextView lastnameTextview = findViewById(R.id.last_name);
        lastnameTextview.setText(user.getLastName());

        String about = user.getAbout();
        if (about != null){
            TextView aboutTextview = findViewById(R.id.car_guest_image);
            aboutTextview.setText(about);
        }

        ArrayList<Car> cars = new ArrayList<>();
        cars = user.getCars();

        if(!cars.isEmpty()){
            //TODO show the user's cars
        }
    }
}
