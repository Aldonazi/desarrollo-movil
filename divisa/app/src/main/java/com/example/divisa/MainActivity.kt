package com.example.divisa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CurrencyConverter()
        }
    }
}

@Composable
fun CurrencyConverter() {
    var input by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    val exchangeRate = 20.88

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = input,
            onValueChange = { input = it },
            label = { Text("Ingresa cantidad") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            result = try {
                val amount = input.toDouble()
                "USD: ${amount / exchangeRate}"
            } catch (e: NumberFormatException) {
                "Entrada inválida"
            }
        }) {
            Text("Convertir a Dólares")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            result = try {
                val amount = input.toDouble()
                "MXN: ${amount * exchangeRate}"
            } catch (e: NumberFormatException) {
                "Entrada inválida"
            }
        }) {
            Text("Convertir a Pesos")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Resultado: $result")
    }
}
