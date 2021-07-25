package com.example.garage;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.garage.databinding.ActivityMainBinding;

import Model.Car;

public class ServicesActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private Car car;

    public ServicesActivity(Car car){
        this.car = car;
    }

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

        repairment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        buyHeadLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buyHeadLightPopupWindow(view);
            }
        });

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ServicesActivity.this, CarActivity.class));
            }
        });

    }

    public void buyHeadLightPopupWindow(View view) {

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_window_buy_head_light, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        // dismiss the popup window when touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }


}
