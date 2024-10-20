package com.example.valuesconverter

import android.os.Bundle
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class KmToMilesFragment : Fragment() {

    private lateinit var editKm: EditText
    private lateinit var textMiles: TextView
    private lateinit var btnSave: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_km_to_miles, container, false)

        editKm = view.findViewById(R.id.editKm)
        textMiles = view.findViewById(R.id.textMiles)
        btnSave = view.findViewById(R.id.btnSave)

        // Set onClickListener for the Save button
        btnSave.setOnClickListener {
            convertAndSave() // Call the convert and save method
        }

        // Load and display the last converted value (if available)
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val lastValue = sharedPref?.getString("last_km_to_miles", "")
        if (!lastValue.isNullOrEmpty()) {
            textMiles.text = "Last converted value: $lastValue"
        }

        return view
    }

    // Method to convert kilometers to miles and save the result
    private fun convertAndSave() {
        val kmStr = editKm.text.toString()
        if (kmStr.isNotEmpty()) {
            val km = kmStr.toDouble()
            val miles = km * 0.621371
            val convertedValue = String.format("%.3f", miles)
            textMiles.text = "Miles: $convertedValue"

            // Save the converted value in SharedPreferences
            val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
            sharedPref?.let {
                with(it.edit()) {
                    putString("last_km_to_miles", convertedValue)
                    apply()
                }
            }
        } else {
            Toast.makeText(context, "Please enter a value", Toast.LENGTH_SHORT).show()
        }
    }
}
