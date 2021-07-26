package com.example.garage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import Controller.*;
import Model.Tools;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import org.json.JSONException;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        login();
    }

    public void login() {
        TextView usernameTextView = findViewById(R.id.username_sign_up);
        String username = usernameTextView.getText().toString();
        TextView passwordTextView = findViewById(R.id.password_sign_up);
        String password = passwordTextView.getText().toString();

        ImageView back = findViewById(R.id.back_image_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });

        try {
            int res = SignAndLog.checkPassword(username, password);

            switch (res) {
                case 0:

                    Tools.exceptionToast(getApplicationContext(), "This username does not exists!");
                case 1:
                    SignAndLog.currentUser = SignAndLog.getUserByUsername(username);
                    startActivity(new Intent(LoginActivity.this, UserActivity.class));
                case -1:
                    Tools.exceptionToast(getApplicationContext(), "Wrong password!");
            }
        } catch (IOException | JSONException | ParseException e) {
            e.printStackTrace();
        }
    }
}
