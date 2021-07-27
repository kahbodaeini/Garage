package com.example.garage;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.garage.databinding.ActivityMainBinding;

import java.io.File;
import java.io.IOException;

import Controller.CarServices;
import Controller.ConfirmBox;
import Controller.SignAndLog;
import Model.Service;
import Model.ServiceType;
import Model.Tools;

public class ServicesActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.services_layout);
        context = this;

        ImageView back = findViewById(R.id.back_image_button);
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
                try {
                    repairmentPopupWindow(view);
                } catch (IOException e) {
                    e.printStackTrace();
                }
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

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ServicesActivity.this, CarActivity.class));
            }
        });

        carWash.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                try {
                    carWashButtonClicked(view);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    public void buyHeadLightPopupWindow(View view) {
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_window, null);
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        ImageButton type1 = popupView.findViewById(R.id.type1);
        ImageButton type2 = popupView.findViewById(R.id.type2);
        ImageButton type3 = popupView.findViewById(R.id.type3);


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
                CarServices carServices = new CarServices(SignAndLog.currentCar);
                try {
                    if(carServices.doService(new Service(SignAndLog.currentCar, ServiceType.LIGHT1)))
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
                CarServices carServices = new CarServices(SignAndLog.currentCar);
                try {
                    if(carServices.doService(new Service(SignAndLog.currentCar, ServiceType.LIGHT2)))
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
                CarServices carServices = new CarServices(SignAndLog.currentCar);
                try {
                    if(carServices.doService(new Service(SignAndLog.currentCar, ServiceType.LIGHT3)))
                        Tools.exceptionToast(getApplicationContext(), "Congratulation On Your New HeadLight!");
                    else
                        Tools.exceptionToast(getApplicationContext(), "Sorry! You Do Not Have Enough Budget To Buy This.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        ImageView back = popupView.findViewById(R.id.back_image_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
    }

    public void buyRingPopupWindow(View view){
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_window, null);
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        ImageButton type1 = popupView.findViewById(R.id.type1);
        ImageButton type2 = popupView.findViewById(R.id.type2);
        ImageButton type3 = popupView.findViewById(R.id.type3);

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
                CarServices carServices = new CarServices(SignAndLog.currentCar);
                try {
                    if(carServices.doService(new Service(SignAndLog.currentCar, ServiceType.RING1)))
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
                CarServices carServices = new CarServices(SignAndLog.currentCar);
                try {
                    if(carServices.doService(new Service(SignAndLog.currentCar, ServiceType.RING2)))
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
                CarServices carServices = new CarServices(SignAndLog.currentCar);
                try {
                    if(carServices.doService(new Service(SignAndLog.currentCar, ServiceType.RING3)))
                        Tools.exceptionToast(getApplicationContext(), "Congratulation On Your New RING!");
                    else
                        Tools.exceptionToast(getApplicationContext(), "Sorry! You Do Not Have Enough Budget To Buy This.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        ImageView back = popupView.findViewById(R.id.back_image_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
    }

    public void buyExhaustPopupWindow(View view){
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_window, null);
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        ImageButton type1 = popupView.findViewById(R.id.type1);
        ImageButton type2 = popupView.findViewById(R.id.type2);
        ImageButton type3 = popupView.findViewById(R.id.type3);

//        File imageFile1 = new File(Service.getImagePath(ServiceType.EXHAUST1));
//        Bitmap bmp1 = BitmapFactory.decodeFile(imageFile1.getAbsolutePath());
//        type1.setImageBitmap(bmp1);
//
//        File imageFile2 = new File(Service.getImagePath(ServiceType.EXHAUST2));
//        Bitmap bmp2 = BitmapFactory.decodeFile(imageFile2.getAbsolutePath());
//        type2.setImageBitmap(bmp2);
//
//        File imageFile3 = new File(Service.getImagePath(ServiceType.EXHAUST3));
//        Bitmap bmp3 = BitmapFactory.decodeFile(imageFile3.getAbsolutePath());
//        type3.setImageBitmap(bmp3);

        type1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                CarServices carServices = new CarServices(SignAndLog.currentCar);
                try {
                    if(carServices.doService(new Service(SignAndLog.currentCar, ServiceType.EXHAUST1)))
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
                CarServices carServices = new CarServices(SignAndLog.currentCar);
                try {
                    if(carServices.doService(new Service(SignAndLog.currentCar, ServiceType.EXHAUST2)))
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
                CarServices carServices = new CarServices(SignAndLog.currentCar);
                try {
                    if(carServices.doService(new Service(SignAndLog.currentCar, ServiceType.EXHAUST3)))
                        Tools.exceptionToast(getApplicationContext(), "Congratulation On Your New EXHAUST!");
                    else
                        Tools.exceptionToast(getApplicationContext(), "Sorry! You Do Not Have Enough Budget To Buy This.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        ImageView back = popupView.findViewById(R.id.back_image_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
    }

    public void buyLeatherPopupWindow(View view){
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_window, null);
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        ImageButton type1 = popupView.findViewById(R.id.type1);
        ImageButton type2 = popupView.findViewById(R.id.type2);
        ImageButton type3 = popupView.findViewById(R.id.type3);

//        File imageFile1 = new File(Service.getImagePath(ServiceType.LEATHER1));
//        Bitmap bmp1 = BitmapFactory.decodeFile(imageFile1.getAbsolutePath());
//        type1.setImageBitmap(bmp1);
//
//        File imageFile2 = new File(Service.getImagePath(ServiceType.LEATHER2));
//        Bitmap bmp2 = BitmapFactory.decodeFile(imageFile2.getAbsolutePath());
//        type2.setImageBitmap(bmp2);
//
//        File imageFile3 = new File(Service.getImagePath(ServiceType.LEATHER3));
//        Bitmap bmp3 = BitmapFactory.decodeFile(imageFile3.getAbsolutePath());
//        type3.setImageBitmap(bmp3);

        type1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                CarServices carServices = new CarServices(SignAndLog.currentCar);
                try {
                    if(carServices.doService(new Service(SignAndLog.currentCar, ServiceType.LEATHER1)))
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
                CarServices carServices = new CarServices(SignAndLog.currentCar);
                try {
                    if(carServices.doService(new Service(SignAndLog.currentCar, ServiceType.LEATHER2)))
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
                CarServices carServices = new CarServices(SignAndLog.currentCar);
                try {
                    if(carServices.doService(new Service(SignAndLog.currentCar, ServiceType.LEATHER3)))
                        Tools.exceptionToast(getApplicationContext(), "Congratulation On Your New LEATHER!");
                    else
                        Tools.exceptionToast(getApplicationContext(), "Sorry! You Do Not Have Enough Budget To Buy This.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        ImageView back = popupView.findViewById(R.id.back_image_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
    }

    public void changeColorPopupWindow(View view){
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_window, null);
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        ImageButton type1 = popupView.findViewById(R.id.type1);
        ImageButton type2 = popupView.findViewById(R.id.type2);
        ImageButton type3 = popupView.findViewById(R.id.type3);

        type1.setBackgroundColor(getResources().getColor(R.color.black));
        type2.setBackgroundColor(getResources().getColor(R.color.white));
        type3.setBackgroundColor(255);


        type1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                CarServices carServices = new CarServices(SignAndLog.currentCar);
                try {
                    if(carServices.doService(new Service(SignAndLog.currentCar, ServiceType.COLOR1)))
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
                CarServices carServices = new CarServices(SignAndLog.currentCar);
                try {
                    if(carServices.doService(new Service(SignAndLog.currentCar, ServiceType.COLOR2)))
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
                CarServices carServices = new CarServices(SignAndLog.currentCar);
                try {
                    if(carServices.doService(new Service(SignAndLog.currentCar, ServiceType.COLOR3)))
                        Tools.exceptionToast(getApplicationContext(), "Congratulation On Your New COLOR!");
                    else
                        Tools.exceptionToast(getApplicationContext(), "Sorry! You Do Not Have Enough Budget To Buy This.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        ImageView back = popupView.findViewById(R.id.back_image_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
    }

    public void improveEnginePopupWindow(View view) {
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_window, null);
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        ImageButton type1 = popupView.findViewById(R.id.type1);
        ImageButton type2 = popupView.findViewById(R.id.type2);
        ImageButton type3 = popupView.findViewById(R.id.type3);
//
//        File imageFile1 = new File(Service.getImagePath(ServiceType.ENGINE1));
//        Bitmap bmp1 = BitmapFactory.decodeFile(imageFile1.getAbsolutePath());
//        type1.setImageBitmap(bmp1);
//
//        File imageFile2 = new File(Service.getImagePath(ServiceType.ENGINE2));
//        Bitmap bmp2 = BitmapFactory.decodeFile(imageFile2.getAbsolutePath());
//        type2.setImageBitmap(bmp2);
//
//        File imageFile3 = new File(Service.getImagePath(ServiceType.ENGINE3));
//        Bitmap bmp3 = BitmapFactory.decodeFile(imageFile3.getAbsolutePath());
//        type3.setImageBitmap(bmp3);

        type1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                CarServices carServices = new CarServices(SignAndLog.currentCar);
                try {
                    if(carServices.doService(new Service(SignAndLog.currentCar, ServiceType.ENGINE1)))
                        Tools.exceptionToast(getApplicationContext(), "Congratulation On Your New ENGINE!");
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
                CarServices carServices = new CarServices(SignAndLog.currentCar);
                try {
                    if(carServices.doService(new Service(SignAndLog.currentCar, ServiceType.ENGINE2)))
                        Tools.exceptionToast(getApplicationContext(), "Congratulation On Your New ENGINE!");
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
                CarServices carServices = new CarServices(SignAndLog.currentCar);
                try {
                    if(carServices.doService(new Service(SignAndLog.currentCar, ServiceType.ENGINE3)))
                        Tools.exceptionToast(getApplicationContext(), "Congratulation On Your New ENGINE!");
                    else
                        Tools.exceptionToast(getApplicationContext(), "Sorry! You Do Not Have Enough Budget To Buy This.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        ImageView back = popupView.findViewById(R.id.back_image_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
    }

    public void carWashButtonClicked(View view) throws IOException {
        if(ConfirmBox.createConfirmBox(context, "Are You Sure You Want To Wash Your Car?")){
            CarServices carServices = new CarServices(SignAndLog.currentCar);
            if(carServices.doService(new Service(SignAndLog.currentCar, ServiceType.CAR_WASH)))
                Tools.exceptionToast(getApplicationContext(), "Your Car Is Clean Now!");
            else
                Tools.exceptionToast(getApplicationContext(), "Sorry! You Do Not Have Enough Budget For The Car Wash.");
        }
    }

    public void repairmentPopupWindow(View view) throws IOException {
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_window_repairment, null);

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        CarServices carServices = new CarServices(SignAndLog.currentCar);
        EditText damagePercentageEdittext = popupView.findViewById(R.id.percent);

        Button done = popupView.findViewById(R.id.done);
        TextView error = popupView.findViewById(R.id.error);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ServiceType serviceType = CarServices.calculateLevelOfRepairment(Integer.parseInt(String.valueOf(damagePercentageEdittext.getText().toString())));
                try {
                    if(carServices.doService(new Service(SignAndLog.currentCar, serviceType)))
                        error.setText("Your Car Is Clean Now!");
                    else
                        error.setText("Sorry! You Do Not Have Enough Budget To Repair Your Car!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        ImageView back = popupView.findViewById(R.id.back_image_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
    }
}

