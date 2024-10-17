package com.example.valuesconverter

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.util.Locale

class KmToMilesFragment : Fragment() {

    private lateinit var etKm: EditText
    private lateinit var etMiles: EditText
    private lateinit var btnSave: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_km_to_miles, container, false)
        etKm = view.findViewById(R.id.etKm)
        etMiles = view.findViewById(R.id.etMiles)
        btnSave = view.findViewById(R.id.btnSaveKmMiles)

        etKm.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (!s.isNullOrEmpty() && !etMiles.isFocused) {
                    val km = s.toString().toDouble()
                    val miles = km * 0.621371
                    etMiles.setText(String.format(Locale.US, "%.2f", miles))
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        etMiles.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (!s.isNullOrEmpty() && !etKm.isFocused) {
                    val miles = s.toString().toDouble()
                    val km = miles / 0.621371
                    etKm.setText(String.format(Locale.US, "%.2f", km))
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        btnSave.setOnClickListener {
            if (etKm.text.isEmpty() || etMiles.text.isEmpty()) {
                Toast.makeText(context, "Please enter values in both fields", Toast.LENGTH_SHORT).show()
            } else {
                // Save to SharedPreferences or handle the save operation
            }
        }

        return view
    }
}

