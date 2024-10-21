package com.example.androiddrawingpad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import com.example.androiddrawingpad.DrawingActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find the button by its ID
        val startButton: Button = findViewById(R.id.startButton)

        // Set a click listener on the button
        startButton.setOnClickListener {
            // Create an Intent to start the DrawingActivity
            val intent = Intent(this, DrawingActivity::class.java)
            startActivity(intent)
        }
    }
}
