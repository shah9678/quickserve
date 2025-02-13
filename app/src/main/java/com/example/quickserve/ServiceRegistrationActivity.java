package com.example.quickserve;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ServiceRegistrationActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private EditText nameField, phoneNumberField, emailField, serviceField, hourlyRateField;
    private ImageView uploadedImage;
    private Button uploadPhotoButton, submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_registration);

        // Initialize UI elements
        nameField = findViewById(R.id.nameField);
        phoneNumberField = findViewById(R.id.phoneNumberField);
        emailField = findViewById(R.id.emailField);
        serviceField = findViewById(R.id.serviceField);
        hourlyRateField = findViewById(R.id.hourlyRateField);
        uploadedImage = findViewById(R.id.uploadedImage);
        uploadPhotoButton = findViewById(R.id.uploadPhotoButton);
        submitButton = findViewById(R.id.submitButton);

        // Upload Photo Button Click
        uploadPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            }
        });

        // Submit Button Click
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameField.getText().toString();
                String phone = phoneNumberField.getText().toString();
                String email = emailField.getText().toString();
                String service = serviceField.getText().toString();
                String hourlyRate = hourlyRateField.getText().toString();

                if (name.isEmpty() || phone.isEmpty() || email.isEmpty() || service.isEmpty() || hourlyRate.isEmpty()) {
                    Toast.makeText(ServiceRegistrationActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ServiceRegistrationActivity.this, "Registration Submitted!", Toast.LENGTH_SHORT).show();
                    // Implement submission logic (e.g., store in database)
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            uploadedImage.setImageURI(data.getData());
        }
    }
}
