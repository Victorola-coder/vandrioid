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

class LbsToKgsFragment : Fragment() {

    private lateinit var editLbs: EditText
    private lateinit var textKgs: TextView
    private lateinit var btnSave: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_lbs_to_kgs, container, false)

        editLbs = view.findViewById(R.id.editLbs)
        textKgs = view.findViewById(R.id.textKgs)
        btnSave = view.findViewById(R.id.btnSave)

        btnSave.setOnClickListener {
            convertAndSave()
        }

        return view
    }

    private fun convertAndSave() {
        val lbsStr = editLbs.text.toString()
        if (lbsStr.isNotEmpty()) {
            val lbs = lbsStr.toDouble()
            val kgs = lbs * 0.453592
            textKgs.text = "KGs: ${String.format("%.3f", kgs)}"
            // TODO: Save to SharedPreferences
        } else {
            Toast.makeText(context, "Please enter a value", Toast.LENGTH_SHORT).show()
        }
    }
}