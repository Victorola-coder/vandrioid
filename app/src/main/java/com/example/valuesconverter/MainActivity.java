// File: app/src/main/java/com/example/valuesconverter/MainActivity.java
package com.example.valuesconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText etKm = findViewById(R.id.etKm);
        EditText etMiles = findViewById(R.id.etMiles);
        Button btnSaveKmMiles = findViewById(R.id.btnSaveKmMiles);
        EditText etLbs = findViewById(R.id.etLbs);
        TextView tvKgs = findViewById(R.id.tvKgs);
        Button btnSaveLbsKgs = findViewById(R.id.btnSaveLbsKgs);
        EditText etFeet = findViewById(R.id.etFeet);
        TextView tvInches = findViewById(R.id.tvInches);
        Button btnSaveFeetInches = findViewById(R.id.btnSaveFeetInches);

        // KM to Miles and vice versa
        etKm.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) {
                    double km = Double.parseDouble(s.toString());
                    double miles = km * 0.621371;
                    etMiles.setText(String.format("%.2f", miles));
                }
            }
            // Implement other required methods of TextWatcher
        });

        etMiles.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) {
                    double miles = Double.parseDouble(s.toString());
                    double km = miles / 0.621371;
                    etKm.setText(String.format("%.2f", km));
                }
            }
            // Implement other required methods of TextWatcher
        });

        btnSaveKmMiles.setOnClickListener(v -> {
            if (etKm.getText().toString().isEmpty() || etMiles.getText().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(), "Please enter both KM and Miles", Toast.LENGTH_SHORT).show();
            } else {
                // Save to SharedPreferences
            }
        });

        // LBS to KGs
        etLbs.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) {
                    double lbs = Double.parseDouble(s.toString());
                    if (lbs < 50 || lbs > 1000) {
                        etLbs.setError("Enter value between 50 and 1000");
                    } else {
                        double kgs = lbs * 0.453592;
                        tvKgs.setText(String.format("%.2f KGs", kgs));
                    }
                }
            }
            // Implement other required methods of TextWatcher
        });

        btnSaveLbsKgs.setOnClickListener(v -> {
            if (etLbs.getText().toString().isEmpty() || tvKgs.getText().toString().equals("KGs will appear here")) {
                Toast.makeText(getApplicationContext(), "Please enter valid LBS", Toast.LENGTH_SHORT).show();
            } else {
                // Save to SharedPreferences
            }
        });

        // Feet to Inches
        etFeet.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) {
                    double feet = Double.parseDouble(s.toString());
                    double inches = feet * 12;
                    tvInches.setText(String.format("%.2f Inches", inches));
                }
            }
            // Implement other required methods of TextWatcher
        });

        btnSaveFeetInches.setOnClickListener(v -> {
            if (etFeet.getText().toString().isEmpty() || tvInches.getText().toString().equals("Inches will appear here")) {
                Toast.makeText(getApplicationContext(), "Please enter Feet", Toast.LENGTH_SHORT).show();
            } else {
                // Save to SharedPreferences
            }
        });
    }
}