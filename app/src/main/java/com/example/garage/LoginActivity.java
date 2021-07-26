package com.example.garage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import org.json.JSONException;
import org.json.simple.parser.ParseException;

import java.io.IOException;

import Controller.SignAndLog;
import Model.User;

public class LoginActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        TextView usernameTextView = findViewById(R.id.username_sign_up);
        TextView passwordTextView = findViewById(R.id.password_sign_up);

        ImageView back = findViewById(R.id.back_image_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });

        TextView errorTextView = findViewById(R.id.error);

        Button done = findViewById(R.id.login_done_button);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (usernameTextView.getText().toString().isEmpty() || passwordTextView.getText().toString().isEmpty()) {
                    errorTextView.setText("please complete all the fields!");
                } else {
                    String username = usernameTextView.getText().toString();
                    String password = passwordTextView.getText().toString();
                    try {
                        int res = SignAndLog.checkPassword(LoginActivity.this.getFilesDir(), username, password);
                        switch (res) {
                            case 0:
                                errorTextView.setText("This username does not exists!");
                                break;
                            case 1:
                                SignAndLog.currentUser = User.getUserByUsername(username);
                                startActivity(new Intent(LoginActivity.this, UserActivity.class));
                                break;
                            case -1:
                                errorTextView.setText("Wrong password!");
                                break;
                        }
                    } catch (IOException | JSONException | ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

}
