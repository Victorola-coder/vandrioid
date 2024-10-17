package com.example.valuesconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.valuesconverter.ui.theme.ValuesConverterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ValuesConverterTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ValuesConverterApp()
                }
            }
        }
    }
}

@Composable
fun ValuesConverterApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "main") {
        composable("main") { MainScreen(navController) }
        composable("km_to_miles") { KmToMilesScreen() }
        composable("feet_to_inches") { FeetToInchesScreen() }
        composable("lbs_to_kgs") { LbsToKgsScreen() }
    }
}

@Composable
fun MainScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Values Converter",
            style = MaterialTheme.typography.headlineMedium
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ConverterButton("KM to Miles", onClick = { navController.navigate("km_to_miles") })
            ConverterButton("Feet to Inches", onClick = { navController.navigate("feet_to_inches") })
            ConverterButton("LBs to KGs", onClick = { navController.navigate("lbs_to_kgs") })
        }
    }
}

@Composable
fun ConverterButton(text: String, onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(text)
    }
}

@Composable
fun KmToMilesScreen() {
    ConverterScreen(
        title = "KM to Miles",
        fromUnit = "KM",
        toUnit = "Miles",
        convert = { it * 0.621371 }
    )
}

@Composable
fun FeetToInchesScreen() {
    ConverterScreen(
        title = "Feet to Inches",
        fromUnit = "Feet",
        toUnit = "Inches",
        convert = { it * 12 }
    )
}

@Composable
fun LbsToKgsScreen() {
    ConverterScreen(
        title = "LBs to KGs",
        fromUnit = "LBs",
        toUnit = "KGs",
        convert = { it * 0.453592 }
    )
}

@Composable
fun ConverterScreen(
    title: String,
    fromUnit: String,
    toUnit: String,
    convert: (Double) -> Double
) {
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = inputValue,
            onValueChange = {
                inputValue = it
                outputValue = try {
                    val input = it.toDouble()
                    String.format("%.3f", convert(input))
                } catch (e: NumberFormatException) {
                    ""
                }
                errorMessage = ""
            },
            label = { Text(fromUnit) },
            keyboardType = KeyboardType.Number,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = outputValue,
            onValueChange = {},
            label = { Text(toUnit) },
            readOnly = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if (inputValue.isBlank()) {
                    errorMessage = "Please enter a value"
                } else {
                    // Save the values (implement this part)
                    errorMessage = ""
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("SAVE")
        }
        if (errorMessage.isNotBlank()) {
            Text(
                text = errorMessage,
                color = Color.Red,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}
