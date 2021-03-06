package com.example.garage;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.garage.databinding.ActivityMainBinding;

import java.io.IOException;
import java.util.Arrays;

import Controller.SignAndLog;
import Model.Car;
import Model.CarType;
import Model.Company;
import Model.Tools;

public class AddCarActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_car);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        ImageView back = findViewById(R.id.back_image_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddCarActivity.this, UserActivity.class));
            }
        });

        Spinner companySpinner = findViewById(R.id.car_company);
        ArrayAdapter<String> companyAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, Arrays.toString(Model.Company.values()).split(", "));
        companyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        companySpinner.setAdapter(companyAdapter);

        Spinner typeSpinner = findViewById(R.id.car_type);
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, Arrays.toString(Model.CarType.values()).split(", "));
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(typeAdapter);

        TextView signTextView = findViewById(R.id.car_sign);

        TextView yearTextView = findViewById(R.id.car_year);

        Spinner colorSpinner = findViewById(R.id.car_color);
        ArrayAdapter<String> colorAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, Arrays.toString(Model.Color.values()).split(", "));
        colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colorSpinner.setAdapter(colorAdapter);

        Button intact = findViewById(R.id.car_intact);
        final int[] flag = {0};
        intact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag[0] % 2 == 0) {
                    intact.setBackgroundColor(Color.parseColor("#04a777"));
                } else {
                    intact.setBackgroundColor(Color.parseColor("#840032"));
                }
                flag[0]++;
            }
        });

        Button done = findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sign = signTextView.getText().toString();
                String year = yearTextView.getText().toString();
                Model.Color color = Model.Color.valueOf(colorSpinner.getSelectedItem().toString());
                CarType type = CarType.valueOf(typeSpinner.getSelectedItem().toString());
                Company company = Company.valueOf(companySpinner.getSelectedItem().toString());

                if (signTextView.getText().equals("") || yearTextView.getText().equals("") ||
                        colorSpinner.getSelectedItem() == null || companySpinner.getSelectedItem() == null ||
                        typeSpinner.getSelectedItem() == null) {
                    Tools.exceptionToast(getApplicationContext(), "Please tell us all about your car first!");
                } else {
                    try {
                        Car newCar = new Car(SignAndLog.currentUser,
                                color, Integer.parseInt(year), flag[0] % 2 != 0, company, type, sign);
                        if (SignAndLog.currentUser.getBudget() >= newCar.calculatePrice()) {
                            SignAndLog.currentUser.setBudget(SignAndLog.currentUser.getBudget() - newCar.calculatePrice());
                            SignAndLog.currentUser.addCar(newCar);
                        }else {
                            Tools.exceptionToast(getApplicationContext(), "Sorry! You Do Not Have Enough Budget To Buy This.");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
