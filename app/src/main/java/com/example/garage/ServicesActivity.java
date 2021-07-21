package com.example.garage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.garage.databinding.ActivityMainBinding;

public class ServicesActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.services_layout);

        Button returnButton = findViewById(R.id.return_button);
        Button repairment = findViewById(R.id.repairment);
        Button buyHeadLight = findViewById(R.id.buy_head_light);
        Button improveEngine = findViewById(R.id.improve_engine);
        Button buyLeather = findViewById(R.id.buy_leather);
        Button buyExhaust = findViewById(R.id.buy_exhaust);
        Button changeColor = findViewById(R.id.change_color);
        Button buyRing = findViewById(R.id.buy_ring);
        Button carWash = findViewById(R.id.car_wash);

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ServicesActivity.this, CarActivity.class));
            }
        });

    }
}
