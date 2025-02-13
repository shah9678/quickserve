package com.example.quickserve;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        Button requestServiceButton = findViewById(R.id.requestServiceButton);
        Button registerServiceProviderButton = findViewById(R.id.registerServiceProviderButton);

        requestServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LaunchActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        registerServiceProviderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LaunchActivity.this, ServiceRegistrationActivity.class);
                startActivity(intent);
            }
        });
    }
}
