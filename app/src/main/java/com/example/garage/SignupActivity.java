package com.example.garage;

import android.os.Bundle;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.TextView;
import Controller.SignAndLog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.garage.databinding.ActivityMainBinding;

import org.json.JSONException;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class SignupActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_layout);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        TextView usernameTextView = findViewById(R.id.userNameTextBoxSignup);
        String username = usernameTextView.getText().toString();
        TextView firstnameTextView = findViewById(R.id.firstNameTextBoxSignup);
        String firstname = firstnameTextView.getText().toString();
        TextView lastnameTextView = findViewById(R.id.lastNameTextBoxSignup);
        String lastname = lastnameTextView.getText().toString();
        TextView passwordTextView = findViewById(R.id.passwordTextBoxSignup);
        String password = passwordTextView.getText().toString();
        TextView budgetTextView = findViewById(R.id.budgetTextBoxSignup);
        double budget = Double.parseDouble(budgetTextView.getText().toString());

        try {
            if(SignAndLog.usernameExists(username))
                SignAndLog.signup(firstname, lastname, username, password, budget);
            else{
                //TO DO
            }
        } catch (IOException | JSONException | ParseException e) {
            e.printStackTrace();
        }

    }
}
