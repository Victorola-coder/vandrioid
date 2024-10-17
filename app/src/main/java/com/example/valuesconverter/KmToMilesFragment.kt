package com.example.valuesconverter

import android.os.Bundle
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

        btnSave.setOnClickListener {
            convertAndSave()
        }

        return view
    }

    private fun convertAndSave() {
        val kmStr = editKm.text.toString()
        if (kmStr.isNotEmpty()) {
            val km = kmStr.toDouble()
            val miles = km * 0.621371
            textMiles.text = "Miles: ${String.format("%.3f", miles)}"
            // TODO: Save to SharedPreferences
        } else {
            Toast.makeText(context, "Please enter a value", Toast.LENGTH_SHORT).show()
        }
    }
}