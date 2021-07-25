package com.example.garage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import Controller.SignAndLog;
import Model.Car;

public class CarsFragment extends Fragment {

    public CarsFragment() {
        // Required empty public constructor
    }

    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_cars, container, false);

        ArrayList<Car> userAllCars = SignAndLog.currentUser.getCars();
        double totalPrice = 0;

        if(!userAllCars.isEmpty())
            for(int i = 0; i < userAllCars.size(); i++)
                totalPrice += userAllCars.get(i).calculatePrice();

        TextView totalPriceTextview = rootView.findViewById(R.id.total_prices);
        totalPriceTextview.setText("Total Prices Of your car: " + totalPrice);

        //TODO handle layout for each car


        return rootView;
    }

}