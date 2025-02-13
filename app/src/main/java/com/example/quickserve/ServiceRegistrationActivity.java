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

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


import org.bson.Document;

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
                EditText nameField = findViewById(R.id.nameField);
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MongoDatabase db = MongoDBHelper.getDatabase();
                MongoCollection<org.bson.Document> usersCollection = db.getCollection("Users");

                Document newUser = new Document("name", nameField.getText().toString())
                        .append("phone", phoneNumberField.getText().toString())
                        .append("email", emailField.getText().toString())
                        .append("service", serviceField.getText().toString())
                        .append("hourlyRate", Integer.parseInt(hourlyRateField.getText().toString()));

                usersCollection.insertOne((org.bson.Document) newUser);
                Toast.makeText(ServiceRegistrationActivity.this, "User Registered!", Toast.LENGTH_SHORT).show();
            }
        });
        MongoDatabase db = MongoDBHelper.getDatabase();
        MongoCollection<org.bson.Document> usersCollection = db.getCollection("Users");

        for (org.bson.Document doc : usersCollection.find()) {
            String name = doc.toString();
            String phone = doc.toString();
            System.out.println("User: " + name + " Phone: " + phone);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            uploadedImage.setImageURI(data.getData());
        }
    }
}
