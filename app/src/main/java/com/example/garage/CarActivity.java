package com.example.garage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
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

import Controller.SignAndLog;
import Model.Car;
import Model.Company;
import Model.Tools;

public class CarActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private final Car car;

    public CarActivity(Car car){
        this.car = car;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.car_layout);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        ImageButton imageButton = findViewById(R.id.car_image);
        File  imageFile = new File(getLogoImagePath(car));
        Bitmap bmp = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
        imageButton.setImageBitmap(bmp);

        TextView carOwnerTextView = findViewById(R.id.car_owner);
        carOwnerTextView.setText(car.getOwner().getUserName());

        TextView carCompanyTextView = findViewById(R.id.car_company);
        carCompanyTextView.setText(car.getCompany().toString());

        TextView carTypeTextView = findViewById(R.id.car_type);
        carTypeTextView.setText(car.getType().toString());

        TextView carSignTextView = findViewById(R.id.car_sign);
        carSignTextView.setText(car.getSign());

        TextView carYearTextView = findViewById(R.id.car_year);
        carYearTextView.setText(car.getYear());

        TextView carColorTextView = findViewById(R.id.car_color);
        carColorTextView.setText(car.getColor().toString());

        TextView carIntactTextView = findViewById(R.id.car_intact);
        if(car.isIntact())
            carIntactTextView.setText("This Car is Totally Intact!");
        else
            carIntactTextView.setText("Unfortunately This Car is Not Intact!");

        TextView carPriceView = findViewById(R.id.car_price);
        carPriceView.setText(String.valueOf(car.calculatePrice()));

        Button sellButton = findViewById(R.id.sell_car);

        Button removeButton = findViewById(R.id.remove_car);

        Button getServicesButton = findViewById(R.id.get_services);

        sellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Random random = new Random();
                boolean isThereABuyer = random.nextBoolean();

                if (isThereABuyer){

                    //TODO Are you sure popup

                    try {
                        SignAndLog.currentUser.setBudget(SignAndLog.currentUser.getBudget() + car.calculatePrice());
                        SignAndLog.currentUser.removeCar(car);

                        //TODO change page to the previous

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else
                    Tools.exceptionToast(getApplicationContext(), "There is No Buyer For Your Car Right Now, Please Try Again Later!");

            }
        });

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    SignAndLog.currentUser.removeCar(car);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        getServicesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //TODO go to service page by making a new ServiceActivity instance
            }
        });


    }

    public static String getLogoImagePath(Car car){

        Company company = car.getCompany();

        String path = "";

        switch (company){
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
        return car;
    }
}
