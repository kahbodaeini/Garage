package com.example.garage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.garage.databinding.ActivityMainBinding;

import java.io.File;

import Controller.SignAndLog;

public class GuestCarActivity extends AppCompatActivity {
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guest_car_layout);

        ImageView back = findViewById(R.id.back_image_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GuestCarActivity.this, AllCarsActivity.class));
            }
        });


        TextView ownerTextView = findViewById(R.id.car_owner);
        ownerTextView.setText(SignAndLog.currentCar.getOwner().getUserName());

        TextView modelTextView = findViewById(R.id.car_model);
        modelTextView.setText(SignAndLog.currentCar.getType().toString() + " " + SignAndLog.currentCar.getCompany().toString());

        TextView colorTextView = findViewById(R.id.car_color);
        colorTextView.setText(SignAndLog.currentCar.getColor().toString());

        ImageButton imageButton = findViewById(R.id.car_guest_image);
        File imageFile = new File(CarActivity.getLogoImagePath(SignAndLog.currentCar));
        Bitmap bmp = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
        imageButton.setImageBitmap(bmp);

    }

}
