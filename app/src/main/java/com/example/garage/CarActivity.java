package com.example.garage;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.garage.databinding.ActivityMainBinding;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import Controller.ConfirmBox;
import Controller.SignAndLog;
import Model.Car;
import Model.Company;
import Model.Tools;

public class CarActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.car_layout);
        context = this;

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        ImageView back = findViewById(R.id.back_image_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CarActivity.this, UserActivity.class));
            }
        });

//        findViewById(R.id.main_layout).setBackgroundColor(Color.parseColor(SignAndLog.currentCar.getCompanyColor()));

        ImageButton imageButton = findViewById(R.id.car_image);
        File imageFile = new File(getLogoImagePath(SignAndLog.currentCar));
        Bitmap bmp = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
        imageButton.setImageBitmap(bmp);

        TextView carOwnerTextView = findViewById(R.id.car_owner);
        carOwnerTextView.setText("OWNER:\t"  + SignAndLog.currentCar.getOwner().getUserName());

        TextView carCompanyTextView = findViewById(R.id.car_company);
        carCompanyTextView.setText("COMPANY:\t" + SignAndLog.currentCar.getCompany().toString());

        TextView carTypeTextView = findViewById(R.id.car_type);
        carTypeTextView.setText("TYPE:\t" + SignAndLog.currentCar.getType().toString());

        TextView carSignTextView = findViewById(R.id.car_sign);
        carSignTextView.setText("SIGN:\t" + SignAndLog.currentCar.getSign());

        TextView carYearTextView = findViewById(R.id.car_year);
        carYearTextView.setText("YEAR:\t" + String.valueOf(SignAndLog.currentCar.getYear()));

        TextView carColorTextView = findViewById(R.id.car_color);
        carColorTextView.setText("COLOR:\t" + SignAndLog.currentCar.getColor().toString());

        TextView carIntactTextView = findViewById(R.id.car_intact);
        if (SignAndLog.currentCar.isIntact())
            carIntactTextView.setText("Intact:\tThis Car is Totally Intact!");
        else
            carIntactTextView.setText("Intact:\tUnfortunately This Car is Not Intact!");

        TextView carPriceView = findViewById(R.id.car_price);
        carPriceView.setText("PRICE:\t" + String.valueOf(SignAndLog.currentCar.calculatePrice()));

        Button sellButton = findViewById(R.id.sell_car);

        Button removeButton = findViewById(R.id.remove_car);

        Button getServicesButton = findViewById(R.id.get_services);

        if (SignAndLog.currentUser != null) {
            sellButton.setBackgroundColor(Color.parseColor("#840032"));
            removeButton.setBackgroundColor(Color.parseColor("#840032"));
            getServicesButton.setBackgroundColor(Color.parseColor("#840032"));

            sellButton.setVisibility(View.VISIBLE);
            sellButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Random random = new Random();
                    boolean isThereABuyer = random.nextBoolean();
                    if (isThereABuyer) {
                        if (ConfirmBox.createConfirmBox(context, "Are You Sure You Want To Sell This Car?")) {
                            try {
                                SignAndLog.currentUser.setBudget(SignAndLog.currentUser.getBudget() + SignAndLog.currentCar.calculatePrice());
                                SignAndLog.currentUser.removeCar(SignAndLog.currentCar);
                                startActivity(new Intent(CarActivity.this, UserActivity.class));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    } else
                        Tools.exceptionToast(getApplicationContext(), "There is No Buyer For Your Car Right Now, Please Try Again Later!");
                }
            });

            removeButton.setVisibility(View.VISIBLE);
            removeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (ConfirmBox.createConfirmBox(context, "Are You Sure You Want To Remove This Car From Garage?")) {
                        try {
                            SignAndLog.currentUser.removeCar(SignAndLog.currentCar);
                            startActivity(new Intent(CarActivity.this, UserActivity.class));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

            getServicesButton.setVisibility(View.VISIBLE);
            getServicesButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(CarActivity.this, ServicesActivity.class));
                }
            });
        } else {
            sellButton.setBackgroundColor(Color.parseColor("#2F4F4F"));
            removeButton.setBackgroundColor(Color.parseColor("#2F4F4F"));
            getServicesButton.setBackgroundColor(Color.parseColor("#2F4F4F"));
        }
    }

    public static boolean createConfirmBox(Context context, String message){
        final boolean[] answer = new boolean[1];
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setMessage(message);
        builder1.setCancelable(true);
        final int[] flag = {0};
        final boolean[] isClicked = {false};

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        answer[0] = true;
                        dialog.cancel();
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        answer[0] = false;
                        dialog.cancel();

                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();

        return answer[0];
    }

    public static String getLogoImagePath(Car car) {

        Company company = car.getCompany();
        String path = "";
        switch (company) {
            case BENZ:
            default:
                path = "/src/main/java/Model/images/benz.jpg";
                break;
            case BMW:
                path = "/src/main/java/Model/images/bmw.jpg";
                break;
            case FIAT:
                path = "/src/main/java/Model/images/fiat.jpg";
                break;
            case AUDI:
                path = "/src/main/java/Model/images/audi.jpg";
                break;
            case FORD:
                path = "/src/main/java/Model/images/ford.jpg";
                break;
            case JEEP:
                path = "/src/main/java/Model/images/jeep.jpg";
                break;
            case HONDA:
                path = "/src/main/java/Model/images/honda.jpg";
                break;
            case LEXUS:
                path = "/src/main/java/Model/images/lexus.jpg";
                break;
            case MAZDA:
                path = "/src/main/java/Model/images/mazda.jpg";
                break;
            case TESLA:
                path = "/src/main/java/Model/images/tesla.png";
                break;
            case VOLVO:
                path = "/src/main/java/Model/images/volvo.jpg";
                break;
            case JAGUAR:
                path = "/src/main/java/Model/images/jaguar.jpg";
                break;
            case NISSAN:
                path = "/src/main/java/Model/images/nissan.jpg";
                break;
            case SUBARU:
                path = "/src/main/java/Model/images/subaru.jpg";
                break;
            case TOYOTA:
                path = "/src/main/java/Model/images/toyota.jpg";
                break;
            case BUGATTI:
                path = "/src/main/java/Model/images/bugatti.jpg";
                break;
            case FERRARI:
                path = "/src/main/java/Model/images/ferrari.jpg";
                break;
            case PEUGEOT:
                path = "/src/main/java/Model/images/peugeot.jpeg";
                break;
            case PORSCHE:
                path = "/src/main/java/Model/images/porsche.jpg";
                break;
            case CADILLAC:
                path = "/src/main/java/Model/images/cadillac.jpg";
                break;
            case MASERATI:
                path = "/src/main/java/Model/images/maserati.jpg";
                break;
            case CHEVROLET:
                path = "/src/main/java/Model/images/chevrolet.jpg";
                break;
            case MITSUBISHI:
                path = "/src/main/java/Model/images/mitsubishi.jpg";
                break;
            case VOLKSWAGEN:
                path = "/src/main/java/Model/images/volkswagen.jpg";
                break;
            case LAMBORGHINI:
                path = "/src/main/java/Model/images/lamborghini.jpg";
                break;
            case ASTON_MARTIN:
                path = "/src/main/java/Model/images/aston martin.jpg";
                break;
        }
        return path;
    }

    public Car getCar() {
        return SignAndLog.currentCar;
    }
}
