package com.example.garage;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import Controller.SignAndLog;
import Model.Tools;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import org.json.JSONException;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class SignUpActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
//    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_layout);
//        binding = ActivityMainBinding.inflate(getLayoutInflater());
        ImageView back = findViewById(R.id.back_image_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this, MainActivity.class));
            }
        });

        Button loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            }
        });

        TextView usernameTextView = findViewById(R.id.username_sign_up);

        TextView firstNameTextView = findViewById(R.id.first_name_sign_up);

        TextView lastNameTextView = findViewById(R.id.last_name_sign_up);

        TextView passwordTextView = findViewById(R.id.password_sign_up);

        TextView budgetTextView = findViewById(R.id.budget_sign_up);

        TextView errorTextView = findViewById(R.id.error);

        Button done = findViewById(R.id.done_button);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (usernameTextView.getText().toString().isEmpty() || firstNameTextView.getText().toString().isEmpty() || lastNameTextView.getText().toString().isEmpty() || passwordTextView.getText().toString().isEmpty() || budgetTextView.getText().toString().isEmpty()) {
                    errorTextView.setText("please complete all the fields!");
                } else {
                    String username = usernameTextView.getText().toString();
                    String firstName = firstNameTextView.getText().toString();
                    String lastName = lastNameTextView.getText().toString();
                    String password = passwordTextView.getText().toString();
                    String budgetString = budgetTextView.getText().toString();

                    try {
                        double budget = Double.parseDouble(budgetString);
                        try {
                            Log.d("FALSE: ", "please");
                            if (!SignAndLog.usernameExists(username)) {
                                SignAndLog.signup(firstName, lastName, username, password, budget);
                                startActivity(new Intent(SignUpActivity.this, UserActivity.class));
                            } else {
                                errorTextView.setText("This username already exists!");
                            }
                        } catch (IOException | JSONException | ParseException e) {
                            e.printStackTrace();
                        }
                    } catch (NumberFormatException ex) {
                        errorTextView.setText("you should enter a valid number for budget!");
                    }
                }
            }
        });

    }

//    public void signup() {
//        TextView usernameTextView = findViewById(R.id.username_sign_up);
//        String username = usernameTextView.getText().toString();
//
//        TextView firstNameTextView = findViewById(R.id.first_name_sign_up);
//        String firstName = firstNameTextView.getText().toString();
//
//        TextView lastNameTextView = findViewById(R.id.last_name_sign_up);
//        String lastName = lastNameTextView.getText().toString();
//
//        TextView passwordTextView = findViewById(R.id.password_sign_up);
//        String password = passwordTextView.getText().toString();
//
//        TextView budgetTextView = findViewById(R.id.budget_sign_up);
//        String budgetString = budgetTextView.getText().toString();
//
//        TextView errorTextView = findViewById(R.id.error);
//
//        Button done = findViewById(R.id.loginDoneButton);
//        done.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (username.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || password.isEmpty() || budgetString.isEmpty()) {
//                    errorTextView.setText("please complete all the fields!");
//                } else {
//                    try {
//                        double budget = Double.parseDouble(budgetString);
//                        try {
//                            if (SignAndLog.usernameExists(username)) {
//                                SignAndLog.signup(firstName, lastName, username, password, budget);
//                                startActivity(new Intent(SignUpActivity.this, MainActivity.class));
//                            } else {
//                                errorTextView.setText("This username already exists!");
//                            }
//                        } catch (IOException | JSONException | ParseException e) {
//                            e.printStackTrace();
//                        }
//                    } catch (NumberFormatException ex) {
//                        errorTextView.setText("you should enter a valid number for budget!");
//                    }
//                }
//            }
//        });
//
//    }

}
