package com.example.androiddrawingpad

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Link to the TextView and Button from XML
        val appName = findViewById<TextView>(R.id.appName)
        val startButton = findViewById<Button>(R.id.startButton)

        // Create the sliding animation for the app name
        ObjectAnimator.ofFloat(appName, "translationX", 1000f).apply {
            duration = 2000 // Slide for 2 seconds
            start()
        }

        // When the button is clicked, go to the DrawingActivity
        startButton.setOnClickListener {
            val intent = Intent(this, DrawingActivity::class.java)
            startActivity(intent)
        }
    }
}
