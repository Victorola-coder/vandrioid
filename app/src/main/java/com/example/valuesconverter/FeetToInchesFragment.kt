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

class FeetToInchesFragment : Fragment() {

    private lateinit var editFeet: EditText
    private lateinit var textInches: TextView
    private lateinit var btnSave: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_feet_to_inches, container, false)

        editFeet = view.findViewById(R.id.editFeet)
        textInches = view.findViewById(R.id.textInches)
        btnSave = view.findViewById(R.id.btnSave)

        btnSave.setOnClickListener {
            convertAndSave(
                val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
                if (sharedPref!= null) {
                    with(sharedPref.edit()) {
                        putString("last_feet_to_inches", convertedValue)
                        apply()
                    }
                }
            )
            
        }
       
    val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
    val lastValue = sharedPref?.getString("last_km_to_miles", "")
    if (!lastValue.isNullOrEmpty()) {
        textMiles.text = "Last converted value: $lastValue"
    }

        return view
    }

    private fun convertAndSave() {
        val feetStr = editFeet.text.toString()
        if (feetStr.isNotEmpty()) {
            val feet = feetStr.toDouble()
            val inches = feet * 12
            textInches.text = "Inches: ${String.format("%.1f", inches)}"
            // TODO: Save to SharedPreferences
        } else {
            Toast.makeText(context, "Please enter a value", Toast.LENGTH_SHORT).show()
        }
    }
}
