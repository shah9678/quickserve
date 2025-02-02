package com.example.quickserve;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.datepicker.MaterialDatePicker;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class BookingActivity extends AppCompatActivity {
    private final String[] tasks = {"Home Cleaner", "Plumber", "Day Caretaker", "Plower", "Mechanic"};
    private final String[] locations = {"Portland, ME", "Westbrook, ME", "Scarborough, ME", "South Portland, ME", "Saco, MA"};

    private final Set<String> selectedTasks = new HashSet<>();
    private String selectedLocation = "";
    private String selectedDate = "";
    private String selectedTime = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        ListView taskListView = findViewById(R.id.taskListView);
        Spinner locationSpinner = findViewById(R.id.locationSpinner);
        Button datePickerButton = findViewById(R.id.datePickerButton);
        Button timePickerButton = findViewById(R.id.timePickerButton);
        Button nextButton = findViewById(R.id.nextButton);
        TextView selectedDateTimeText = findViewById(R.id.selectedDateTimeText);

        // Task selection logic
        ArrayAdapter<String> taskAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, tasks);
        taskListView.setAdapter(taskAdapter);
        taskListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        taskListView.setOnItemClickListener((parent, view, position, id) -> {
            String task = tasks[position];
            if (selectedTasks.contains(task)) {
                selectedTasks.remove(task);
            } else {
                selectedTasks.add(task);
            }
        });

        // Location dropdown logic
        ArrayAdapter<String> locationAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, locations);
        locationSpinner.setAdapter(locationAdapter);

        locationSpinner.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, android.view.View view, int position, long id) {
                selectedLocation = locations[position];
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {
                selectedLocation = "";
            }
        });

        // Date picker logic
        MaterialDatePicker<Long> datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select Date")
                .build();

        datePickerButton.setOnClickListener(v -> datePicker.show(getSupportFragmentManager(), "DATE_PICKER"));

        datePicker.addOnPositiveButtonClickListener(selection -> {
            selectedDate = datePicker.getHeaderText();
            updateSelectedDateTimeText(selectedDateTimeText);
        });

        // Time picker logic
        timePickerButton.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);

            new android.app.TimePickerDialog(this, (view, hourOfDay, minuteOfHour) -> {
                selectedTime = String.format("%02d:%02d", hourOfDay, minuteOfHour);
                updateSelectedDateTimeText(selectedDateTimeText);
            }, hour, minute, true).show();
        });

        // Next button logic
        nextButton.setOnClickListener(v -> {
            if (selectedTasks.isEmpty() || selectedLocation.isEmpty() || selectedDate.isEmpty() || selectedTime.isEmpty()) {
                Toast.makeText(this, "Please complete all fields", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Booking Confirmed!", Toast.LENGTH_SHORT).show();
                // Navigate to the next screen or process booking logic here
            }
        });
    }

    private void updateSelectedDateTimeText(TextView selectedDateTimeText) {
        String dateTime = "Selected Date: " + selectedDate + "\nSelected Time: " + selectedTime;
        selectedDateTimeText.setText(dateTime);
    }
}
