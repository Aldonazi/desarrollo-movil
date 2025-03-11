package com.example.planeta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.background
import com.example.planeta.ui.theme.PlanetaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlanetaTheme {
                PlanetaApp()
            }
        }
    }
}

@Composable
fun PlanetaApp() {
    var selectedPlanet by remember { mutableStateOf<String?>(null) }
    var weight by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (selectedPlanet == null) {
            Text(
                "Selecciona un planeta",
                fontSize = 24.sp,
                color = Color.White,
                modifier = Modifier.padding(16.dp)
            )
            PlanetSelection { selectedPlanet = it }
        } else {
            PlanetDetail(
                planet = selectedPlanet!!,
                weight = weight,
                onWeightChange = { weight = it },
                onBack = { selectedPlanet = null }
            )
        }
    }
}

@Composable
fun PlanetSelection(onPlanetSelected: (String) -> Unit) {
    val planets = listOf("luna" to R.drawable.luna, "marte" to R.drawable.marte, "saturno" to R.drawable.saturno)

    Column {
        planets.forEach { (name, imageRes) ->
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clickable { onPlanetSelected(name) }
                    .padding(8.dp)
            )
        }
    }
}

@Composable
fun PlanetDetail(planet: String, weight: String, onWeightChange: (String) -> Unit, onBack: () -> Unit) {
    val planetImages = mapOf(
        "luna" to R.drawable.luna,
        "marte" to R.drawable.marte,
        "saturno" to R.drawable.saturno
    )
    val gravityMultipliers = mapOf(
        "luna" to 0.165,
        "marte" to 0.38,
        "saturno" to 1.065
    )
    val planetDescriptions = mapOf(
        "luna" to """
            • Su diámetro es de 3,476 km.
            • Está a 384,400 km de la Tierra.
            • Tarda 27.32 días en orbitar la Tierra.
            • Siempre muestra la misma cara a la Tierra.
            • No brilla con luz propia, refleja la luz del Sol.
            • Cubierta de cráteres y escombros rocosos.
        """.trimIndent(),
        "marte" to """
            • Temperatura promedio de -65° C.
            • Atmósfera delgada con dióxido de carbono.
            • Tiene dos satélites: Fobos y Deimos.
            • Superficie sólida, polvorienta y desértica.
            • Posee estaciones, volcanes y cañones.
        """.trimIndent(),
        "saturno" to """
            • Gigante gaseoso compuesto de hidrógeno y helio.
            • Atmósfera densa, con colores amarillo pálido y gris azulado.
            • Sistema de anillos planos y con forma de disco.
            • Rota en poco más de 10 horas.
            • Se abulta en el ecuador, pareciendo una bola achatada.
        """.trimIndent()
    )

    val weightOnPlanet = weight.toDoubleOrNull()?.let { it * (gravityMultipliers[planet] ?: 1.0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = onBack, modifier = Modifier.padding(bottom = 16.dp)) {
            Text("Regresar")
        }

        Image(
            painter = painterResource(id = planetImages[planet]!!),
            contentDescription = planet,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            planetDescriptions[planet] ?: "",
            fontSize = 16.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = weight,
            onValueChange = onWeightChange,
            label = { Text("Ingresa tu peso en kg", color = Color.White) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            textStyle = LocalTextStyle.current.copy(color = Color.White),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        weightOnPlanet?.let {
            Text(
                "Tu peso en $planet es: ${"%.2f".format(it)} kg",
                fontSize = 18.sp,
                color = Color.White
            )
        }
    }
}

