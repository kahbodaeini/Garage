package com.example.garage;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import org.json.JSONException;

import java.io.IOException;

import Controller.SignAndLog;
import Model.User;

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
                            if (User.getUserByUsername(username) == null) {
                                SignAndLog.signup(firstName, lastName, username, password, budget);
                                startActivity(new Intent(SignUpActivity.this, UserActivity.class));
                            } else {
                                errorTextView.setText("This username already exists!");
                            }
                        } catch (IOException | JSONException e) {
                            e.printStackTrace();
                        }
                    } catch (NumberFormatException ex) {
                        errorTextView.setText("you should enter a valid number for budget!");
                    }
                }
            }
        });

    }

}
