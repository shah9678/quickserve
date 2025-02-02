package com.example.quickserve;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button btnLogin = findViewById(R.id.btnLogin);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button btnSignup = findViewById(R.id.btnSignup);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageButton btnLoginWithApple = findViewById(R.id.btnLoginWithApple);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageButton btnLoginWithGoogle = findViewById(R.id.btnLoginWithGoogle);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageButton btnLoginWithFacebook = findViewById(R.id.btnLoginWithFacebook);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (!username.isEmpty() && !password.isEmpty()) {
                    // Perform login validation
                    Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                    // Navigate to ContentView
                    startActivity(new Intent(LoginActivity.this, BookingActivity.class));
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to SignupActivity
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });

        btnLoginWithApple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform login with Apple logic
                Toast.makeText(LoginActivity.this, "Login with Apple", Toast.LENGTH_SHORT).show();
            }
        });

        btnLoginWithGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform login with Google logic
                Toast.makeText(LoginActivity.this, "Login with Google", Toast.LENGTH_SHORT).show();
            }
        });

        btnLoginWithFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform login with Facebook logic
                Toast.makeText(LoginActivity.this, "Login with Facebook", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
