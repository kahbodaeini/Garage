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

import org.w3c.dom.Text;

import java.io.File;

import Model.Car;

public class GuestCarActivity extends AppCompatActivity {
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private final Car car;

    public GuestCarActivity(Car car){
        this.car = car;
    }

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


        TextView ownerTextview = findViewById(R.id.car_owner);
        ownerTextview.setText(car.getOwner().getUserName());

        TextView modelTextview = findViewById(R.id.car_model);
        modelTextview.setText(car.getType().toString() + " " + car.getCompany().toString());

        TextView colorTextview = findViewById(R.id.car_color);
        colorTextview.setText(car.getColor().toString());

        ImageButton imageButton = findViewById(R.id.car_guest_image);
        File imageFile = new File(CarActivity.getLogoImagePath(car));
        Bitmap bmp = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
        imageButton.setImageBitmap(bmp);

    }

}
