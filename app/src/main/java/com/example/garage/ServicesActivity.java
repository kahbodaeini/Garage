package com.example.garage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.garage.databinding.ActivityMainBinding;

import java.io.File;
import java.io.IOException;

import Controller.CarServices;
import Model.Car;
import Model.Service;
import Model.ServiceType;
import Model.Tools;

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

        buyRing.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                buyRingPopupWindow(view);
            }
        });

        buyExhaust.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                buyExhaustPopupWindow(view);
            }
        });

        buyLeather.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                buyLeatherPopupWindow(view);
            }
        });

        changeColor.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                changeColorPopupWindow(view);
            }
        });

        improveEngine.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                improveEnginePopupWindow(view);
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

        ImageButton type1 = findViewById(R.id.type1);
        ImageButton type2 = findViewById(R.id.type2);
        ImageButton type3 = findViewById(R.id.type3);

        File imageFile1 = new File(Service.getImagePath(ServiceType.LIGHT1));
        Bitmap bmp1 = BitmapFactory.decodeFile(imageFile1.getAbsolutePath());
        type1.setImageBitmap(bmp1);

        File imageFile2 = new File(Service.getImagePath(ServiceType.LIGHT2));
        Bitmap bmp2 = BitmapFactory.decodeFile(imageFile2.getAbsolutePath());
        type2.setImageBitmap(bmp2);

        File imageFile3 = new File(Service.getImagePath(ServiceType.LIGHT3));
        Bitmap bmp3 = BitmapFactory.decodeFile(imageFile3.getAbsolutePath());
        type3.setImageBitmap(bmp3);

        type1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                CarServices carServices = new CarServices(car);
                try {
                    if(carServices.doService(new Service(car, ServiceType.LIGHT1)))
                        Tools.exceptionToast(getApplicationContext(), "Congratulation On Your New HeadLight!");
                        else
                            Tools.exceptionToast(getApplicationContext(), "Sorry! You Do Not Have Enough Budget To Buy This.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        type2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                CarServices carServices = new CarServices(car);
                try {
                    if(carServices.doService(new Service(car, ServiceType.LIGHT2)))
                        Tools.exceptionToast(getApplicationContext(), "Congratulation On Your New HeadLight!");
                    else
                        Tools.exceptionToast(getApplicationContext(), "Sorry! You Do Not Have Enough Budget To Buy This.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        type3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                CarServices carServices = new CarServices(car);
                try {
                    if(carServices.doService(new Service(car, ServiceType.LIGHT3)))
                        Tools.exceptionToast(getApplicationContext(), "Congratulation On Your New HeadLight!");
                    else
                        Tools.exceptionToast(getApplicationContext(), "Sorry! You Do Not Have Enough Budget To Buy This.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_window, null);

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);


        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }



    public void buyRingPopupWindow(View view){
        ImageButton type1 = findViewById(R.id.type1);
        ImageButton type2 = findViewById(R.id.type2);
        ImageButton type3 = findViewById(R.id.type3);

        File imageFile1 = new File(Service.getImagePath(ServiceType.RING1));
        Bitmap bmp1 = BitmapFactory.decodeFile(imageFile1.getAbsolutePath());
        type1.setImageBitmap(bmp1);

        File imageFile2 = new File(Service.getImagePath(ServiceType.RING2));
        Bitmap bmp2 = BitmapFactory.decodeFile(imageFile2.getAbsolutePath());
        type2.setImageBitmap(bmp2);

        File imageFile3 = new File(Service.getImagePath(ServiceType.RING3));
        Bitmap bmp3 = BitmapFactory.decodeFile(imageFile3.getAbsolutePath());
        type3.setImageBitmap(bmp3);

        type1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                CarServices carServices = new CarServices(car);
                try {
                    if(carServices.doService(new Service(car, ServiceType.RING1)))
                        Tools.exceptionToast(getApplicationContext(), "Congratulation On Your New RING!");
                    else
                        Tools.exceptionToast(getApplicationContext(), "Sorry! You Do Not Have Enough Budget To Buy This.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        type2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                CarServices carServices = new CarServices(car);
                try {
                    if(carServices.doService(new Service(car, ServiceType.RING2)))
                        Tools.exceptionToast(getApplicationContext(), "Congratulation On Your New RING!");
                    else
                        Tools.exceptionToast(getApplicationContext(), "Sorry! You Do Not Have Enough Budget To Buy This.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        type3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                CarServices carServices = new CarServices(car);
                try {
                    if(carServices.doService(new Service(car, ServiceType.RING3)))
                        Tools.exceptionToast(getApplicationContext(), "Congratulation On Your New RING!");
                    else
                        Tools.exceptionToast(getApplicationContext(), "Sorry! You Do Not Have Enough Budget To Buy This.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_window, null);

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);


        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }

    public void buyExhaustPopupWindow(View view){
        ImageButton type1 = findViewById(R.id.type1);
        ImageButton type2 = findViewById(R.id.type2);
        ImageButton type3 = findViewById(R.id.type3);

        File imageFile1 = new File(Service.getImagePath(ServiceType.EXHAUST1));
        Bitmap bmp1 = BitmapFactory.decodeFile(imageFile1.getAbsolutePath());
        type1.setImageBitmap(bmp1);

        File imageFile2 = new File(Service.getImagePath(ServiceType.EXHAUST2));
        Bitmap bmp2 = BitmapFactory.decodeFile(imageFile2.getAbsolutePath());
        type2.setImageBitmap(bmp2);

        File imageFile3 = new File(Service.getImagePath(ServiceType.EXHAUST3));
        Bitmap bmp3 = BitmapFactory.decodeFile(imageFile3.getAbsolutePath());
        type3.setImageBitmap(bmp3);

        type1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                CarServices carServices = new CarServices(car);
                try {
                    if(carServices.doService(new Service(car, ServiceType.EXHAUST1)))
                        Tools.exceptionToast(getApplicationContext(), "Congratulation On Your New EXHAUST!");
                    else
                        Tools.exceptionToast(getApplicationContext(), "Sorry! You Do Not Have Enough Budget To Buy This.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        type2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                CarServices carServices = new CarServices(car);
                try {
                    if(carServices.doService(new Service(car, ServiceType.EXHAUST2)))
                        Tools.exceptionToast(getApplicationContext(), "Congratulation On Your New EXHAUST!");
                    else
                        Tools.exceptionToast(getApplicationContext(), "Sorry! You Do Not Have Enough Budget To Buy This.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        type3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                CarServices carServices = new CarServices(car);
                try {
                    if(carServices.doService(new Service(car, ServiceType.EXHAUST3)))
                        Tools.exceptionToast(getApplicationContext(), "Congratulation On Your New EXHAUST!");
                    else
                        Tools.exceptionToast(getApplicationContext(), "Sorry! You Do Not Have Enough Budget To Buy This.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_window, null);

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);


        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }

    public void buyLeatherPopupWindow(View view){
        ImageButton type1 = findViewById(R.id.type1);
        ImageButton type2 = findViewById(R.id.type2);
        ImageButton type3 = findViewById(R.id.type3);

        File imageFile1 = new File(Service.getImagePath(ServiceType.LEATHER1));
        Bitmap bmp1 = BitmapFactory.decodeFile(imageFile1.getAbsolutePath());
        type1.setImageBitmap(bmp1);

        File imageFile2 = new File(Service.getImagePath(ServiceType.LEATHER2));
        Bitmap bmp2 = BitmapFactory.decodeFile(imageFile2.getAbsolutePath());
        type2.setImageBitmap(bmp2);

        File imageFile3 = new File(Service.getImagePath(ServiceType.LEATHER3));
        Bitmap bmp3 = BitmapFactory.decodeFile(imageFile3.getAbsolutePath());
        type3.setImageBitmap(bmp3);

        type1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                CarServices carServices = new CarServices(car);
                try {
                    if(carServices.doService(new Service(car, ServiceType.LEATHER1)))
                        Tools.exceptionToast(getApplicationContext(), "Congratulation On Your New LEATHER!");
                    else
                        Tools.exceptionToast(getApplicationContext(), "Sorry! You Do Not Have Enough Budget To Buy This.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        type2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                CarServices carServices = new CarServices(car);
                try {
                    if(carServices.doService(new Service(car, ServiceType.LEATHER2)))
                        Tools.exceptionToast(getApplicationContext(), "Congratulation On Your New LEATHER!");
                    else
                        Tools.exceptionToast(getApplicationContext(), "Sorry! You Do Not Have Enough Budget To Buy This.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        type3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                CarServices carServices = new CarServices(car);
                try {
                    if(carServices.doService(new Service(car, ServiceType.LEATHER3)))
                        Tools.exceptionToast(getApplicationContext(), "Congratulation On Your New LEATHER!");
                    else
                        Tools.exceptionToast(getApplicationContext(), "Sorry! You Do Not Have Enough Budget To Buy This.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_window, null);

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);


        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }

    public void changeColorPopupWindow(View view){
        ImageButton type1 = findViewById(R.id.type1);
        ImageButton type2 = findViewById(R.id.type2);
        ImageButton type3 = findViewById(R.id.type3);

        type1.setBackgroundColor(getResources().getColor(R.color.black));
        type2.setBackgroundColor(getResources().getColor(R.color.white));
        type3.setBackgroundColor(255);


        type1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                CarServices carServices = new CarServices(car);
                try {
                    if(carServices.doService(new Service(car, ServiceType.COLOR)))
                        Tools.exceptionToast(getApplicationContext(), "Congratulation On Your New COLOR!");
                    else
                        Tools.exceptionToast(getApplicationContext(), "Sorry! You Do Not Have Enough Budget To Buy This.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        type2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                CarServices carServices = new CarServices(car);
                try {
                    if(carServices.doService(new Service(car, ServiceType.COLOR)))
                        Tools.exceptionToast(getApplicationContext(), "Congratulation On Your New COLOR!");
                    else
                        Tools.exceptionToast(getApplicationContext(), "Sorry! You Do Not Have Enough Budget To Buy This.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        type3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                CarServices carServices = new CarServices(car);
                try {
                    if(carServices.doService(new Service(car, ServiceType.COLOR)))
                        Tools.exceptionToast(getApplicationContext(), "Congratulation On Your New COLOR!");
                    else
                        Tools.exceptionToast(getApplicationContext(), "Sorry! You Do Not Have Enough Budget To Buy This.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_window, null);

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);


        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }

}
