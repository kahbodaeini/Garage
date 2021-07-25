package com.example.garage;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.ArrayList;

import Controller.ConfirmBox;
import Model.Car;
import Model.User;

public class UserActivity extends AppCompatActivity {

    private final User user;

    public UserActivity(User user){
        this.user = user;
    }

    TabLayout tabLayout;
    FrameLayout frameLayout;
    Fragment fragment = null;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout=(TabLayout)findViewById(R.id.tabLayout);
        frameLayout=(FrameLayout)findViewById(R.id.frameLayout);

        fragment = new ProfileFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // Fragment fragment = null;
                switch (tab.getPosition()) {
                    case 0:
                        fragment = new ProfileFragment();

                        TextView budgetTextview = findViewById(R.id.budget);
                        budgetTextview.setText(String.valueOf(user.getBudget()));

                        TextView usernameTextview = findViewById(R.id.username);
                        usernameTextview.setText(user.getUserName());

                        String imagePath = user.getImagePath();
                        if(imagePath != null)
                            Glide.with(UserActivity.this)
                                    .load(imagePath)
                                    .into((ImageView) findViewById(R.id.imageView));

                        break;
                    case 1:
                        fragment = new CarsFragment();
                        ArrayList<Car> cars = new ArrayList<>();
                        cars = user.getCars();
                        double totalPrice = 0;

                        if (!cars.isEmpty()){
                            for(int i = 0; i < cars.size(); i++)
                                totalPrice += cars.get(i).calculatePrice();
                        }

                        TextView totalPriceTextview = findViewById(R.id.total_prices);
                        totalPriceTextview.setText("Total Prices Of your car: " + totalPrice);
                        break;
                }
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frameLayout, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
            }

//            private void changePasswordButtonClicked(){
//                //TODO popup to get current password and new password
//
//                String oldPassword, newPassword;
//
//                boolean isOk = user.changePassword(oldPassword, newPassword);
//
//                if(isOk){
//                    //TODO popup that says password is changed
//                }
//                else{
//                    //TODO popup that says wrong password
//                }
//            }
//
//            private void addBudgetButtonClicked() throws IOException {
//
//                String inputBudget;
//                double budget = Double.parseDouble(inputBudget);
//
//                user.setBudget(user.getBudget() + budget);
//
//            }
//
//            private void changeUsernameButtonClicked(){
//
//                String inputNewUsername;
//                user.setUserName(inputNewUsername);
//
//                //TODO popup that says username changed
//            }
//
//            private void addAboutButtonClicked(){
//
//                String newAbout;
//                user.setAbout(newAbout);
//            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}