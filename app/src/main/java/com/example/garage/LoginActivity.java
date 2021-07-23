package com.example.garage;

import android.os.Bundle;
import android.widget.TextView;

import Controller.*;
import Model.Tools;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.garage.databinding.ActivityMainBinding;

import org.json.JSONException;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        login();
    }

    public void login() {

        TextView usernameTextView = findViewById(R.id.userNameTextBoxSignup);
        String username = usernameTextView.getText().toString();
        TextView passwordTextView = findViewById(R.id.passwordTextBoxSignup);
        String password = passwordTextView.getText().toString();

        try {
            int res = SignAndLog.checkPassword(username, password);

            switch (res) {
                case 0:

                    Tools.exceptionToast(getApplicationContext(), "This username does not exists!");
                case 1:
                    //you can login
                    SignAndLog.currentUser = SignAndLog.getUserByUsername(username);
                case -1:
                    Tools.exceptionToast(getApplicationContext(), "Wrong password!");

            }
        } catch (IOException | JSONException | ParseException e) {
            e.printStackTrace();
        }
    }
}
