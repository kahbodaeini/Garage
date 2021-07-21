package com.example.garage;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.garage.databinding.ActivityMainBinding;

import Model.Color;

public class AddCarActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_car);

        Spinner company = findViewById(R.id.car_company);

        Spinner type = findViewById(R.id.car_type);

        TextView signTextView = findViewById(R.id.car_sign);
        String sign = signTextView.getText().toString();

        TextView yearTextView = findViewById(R.id.car_year);
        String year = signTextView.getText().toString();

        Spinner color = findViewById(R.id.car_color);

        Button intact = findViewById(R.id.car_intact);

        Button done = findViewById(R.id.done);

        final int[] flag = {0};
        intact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag[0] % 2 == 0){
                    intact.setBackgroundColor(android.graphics.Color.green(10));
                }else {
                    intact.setBackgroundColor(android.graphics.Color.red(10));
                }
                flag[0]++;
            }
        });


    }

}
