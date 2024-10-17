package com.example.valuesconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnKmToMiles: Button = findViewById(R.id.btnKmToMiles)
        val btnFeetToInches: Button = findViewById(R.id.btnFeetToInches)
        val btnLbsToKgs: Button = findViewById(R.id.btnLbsToKgs)

        btnKmToMiles.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, KmToMilesFragment())
                .addToBackStack(null)
                .commit()
        }

        btnFeetToInches.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, FeetToInchesFragment())
                .addToBackStack(null)
                .commit()
        }

        btnLbsToKgs.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, LbsToKgsFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}