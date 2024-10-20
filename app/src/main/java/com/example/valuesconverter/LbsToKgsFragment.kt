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
import android.util.Log

class LbsToKgsFragment : Fragment() {

    private lateinit var editLbs: EditText
    private lateinit var textKgs: TextView
    private lateinit var btnSave: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_lbs_to_kgs, container, false)

        editLbs = view.findViewById(R.id.editLbs)
        textKgs = view.findViewById(R.id.textKgs)
        btnSave = view.findViewById(R.id.btnSave)

        // Set onClickListener for the Save button
        btnSave.setOnClickListener {
            convertAndSave() // Call the convert and save method
        }

        // Load and display the last converted value (if available)
        val sharedPref = activity?.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val lastValue = sharedPref?.getString("last_lbs_to_kgs", "")
        if (!lastValue.isNullOrEmpty()) {
            textKgs.text = "Last converted value: $lastValue"
        }

        return view
    }

    // Method to convert pounds to kilograms and save the result
    private fun convertAndSave() {
        val lbsStr = editLbs.text.toString()
        if (lbsStr.isNotEmpty()) {
            val lbs = lbsStr.toDouble()
            
            // Input validation
            when {
                lbs < 50 -> {
                    Toast.makeText(context, "Value must be at least 50 LBS", Toast.LENGTH_SHORT).show()
                    return
                }
                lbs > 1000 -> {
                    Toast.makeText(context, "Value must not exceed 1000 LBS", Toast.LENGTH_SHORT).show()
                    return
                }
            }

            val kgs = lbs * 0.453592
            val convertedValue = String.format("%.3f", kgs)
            textKgs.text = "KGs: $convertedValue"

            // Save the converted value in SharedPreferences
            val sharedPref = activity?.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
            sharedPref?.edit()?.apply {
                putString("last_lbs_to_kgs", convertedValue)
                apply()
            }

            // Log the saved value
            Log.d("LbsToKgsFragment", "Saved value: $convertedValue")

            // Verify the saved value
            val savedValue = sharedPref?.getString("last_lbs_to_kgs", "")
            if (savedValue == convertedValue) {
                Log.d("LbsToKgsFragment", "Value successfully saved and verified")
            } else {
                Log.e("LbsToKgsFragment", "Error: Saved value does not match converted value")
            }

        } else {
            Toast.makeText(context, "Please enter a value", Toast.LENGTH_SHORT).show()
        }
    }
}
