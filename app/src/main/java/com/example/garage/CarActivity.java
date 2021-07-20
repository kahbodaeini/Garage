package com.example.garage;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.garage.databinding.ActivityMainBinding;

import Model.Car;
import Model.Company;

public class CarActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.car_layout);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

    }

    public String getLogoImagePath(Car car){

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
}
