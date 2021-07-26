package com.example.garage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import Controller.SignAndLog;
import Model.Tools;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.garage.databinding.ActivityMainBinding;

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
        signup();
    }

    public void signup(){
        ImageView back = findViewById(R.id.back_image_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this, MainActivity.class));
            }
        });

        TextView usernameTextView = findViewById(R.id.userNameTextBoxSignup);
        String username = usernameTextView.getText().toString();
        TextView firstNameTextView = findViewById(R.id.all_users);
        String firstName = firstNameTextView.getText().toString();
        TextView lastNameTextView = findViewById(R.id.all_cars);
        String lastName = lastNameTextView.getText().toString();
        TextView passwordTextView = findViewById(R.id.passwordTextBoxSignup);
        String password = passwordTextView.getText().toString();
        TextView budgetTextView = findViewById(R.id.budgetTextBoxSignup);
        double budget = Double.parseDouble(budgetTextView.getText().toString());

        try {
            if(SignAndLog.usernameExists(username))
                SignAndLog.signup(firstName, lastName, username, password, budget);
            else{
                Tools.exceptionToast(getApplicationContext(), "This username already exists!");
            }
        } catch (IOException | JSONException | ParseException e) {
            e.printStackTrace();
        }
    }
}
