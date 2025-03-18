package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.compose.foundation.clickable


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    Scaffold { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") { HomeScreen(navController) }
            composable("recommendations/{category}") { backStackEntry ->
                RecommendationScreen(navController, backStackEntry.arguments?.getString("category") ?: "")
            }
            composable("details/{place}") { backStackEntry ->
                DetailScreen(place = backStackEntry.arguments?.getString("place") ?: "")
            }
        }
    }
}

@Composable
fun HomeScreen(navController: NavHostController) {
    Column(modifier = Modifier.padding(16.dp)) {
        listOf("Cafeterías", "Plazas", "Parques").forEach { category ->
            Text(
                text = category,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { navController.navigate("recommendations/$category") }
            )
        }
    }
}

@Composable
fun RecommendationScreen(navController: NavHostController, category: String) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "$category Recomendaciones", style = MaterialTheme.typography.headlineMedium)

        when (category) {
            "Cafeterías" -> {
                Text(
                    text = "Cafetería Bola de Oro",
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable { navController.navigate("details/Cafetería Bola de Oro") }
                )
            }
            "Plazas" -> {
                Text(
                    text = "Plaza Las Américas",
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable { navController.navigate("details/Plaza Las Américas") }
                )
            }
            "Parques" -> {
                Text(
                    text = "Parque Juárez",
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable { navController.navigate("details/Parque Juárez") }
                )
            }
        }
    }
}

@Composable
fun DetailScreen(place: String) {
    Column(modifier = Modifier.padding(16.dp)) {
        when (place) {
            "Cafetería Bola de Oro" -> {
                Image(
                    painter = painterResource(id = R.drawable.bola), // Imagen de la cafetería
                    contentDescription = "Cafetería Bola de Oro",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp), // Ajusta la altura según necesites
                    contentScale = ContentScale.Crop // Ajusta la imagen
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Cafetería Bola de Oro - Descripción", style = MaterialTheme.typography.headlineMedium)
                Text(
                    text = "La historia de la cafetería Bola de Oro de Xalapa, Veracruz, se remonta a principios del siglo XX con la fundación de un negocio de tostado y venta de café por parte de don Porfirio Hernández.\n\n" +
                            "Fundación\nEl primer tostador de café de don Porfirio se estableció en la calle de Enríquez, en el centro de Xalapa.\n" +
                            "El nombre 'Bola de Oro' se debe a la forma redonda y dorada que adquiere el grano de café tostado.\n" +
                            "El negocio de don Porfirio tuvo un gran éxito en la región.",
                    modifier = Modifier.padding(8.dp)
                )
            }
            "Plaza Las Américas" -> {
                Image(
                    painter = painterResource(id = R.drawable.americas), // Imagen de la plaza
                    contentDescription = "Plaza Las Américas",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp), // Ajusta la altura según necesites
                    contentScale = ContentScale.Crop // Ajusta la imagen
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Plaza Las Américas - Descripción", style = MaterialTheme.typography.headlineMedium)
                Text(
                    text = "La historia de Plaza Las Américas en Puerto Rico comienza en 1918, cuando los hermanos Fonalledas compraron 527 acres de tierra.\n\n" +
                            "Compra de la tierra\nEn 1918, los hermanos Fonalledas compraron la tierra conocida como 'Las Monjas'.\n" +
                            "La tierra era propiedad de Don Pablo Ubarri Capetillo, el Conde de Santurce.\n" +
                            "Poco a poco, se eliminó la cosecha de caña para utilizar las tierras para la creación de una empresa lechera.",
                    modifier = Modifier.padding(8.dp)
                )
            }
            "Parque Juárez" -> {
                Image(
                    painter = painterResource(id = R.drawable.juarez), // Imagen del parque
                    contentDescription = "Parque Juárez",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp), // Ajusta la altura según necesites
                    contentScale = ContentScale.Crop // Ajusta la imagen
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Parque Juárez - Descripción", style = MaterialTheme.typography.headlineMedium)
                Text(
                    text = "El parque Juárez es un parque público en la ciudad de Xalapa, en el estado de Veracruz, México, inaugurado en 1892 y nombrado en honor de Benito Juárez.\n\n" +
                            "Coordenadas: 19°31′34″N 96°55′26″O﻿ / ﻿19.52611111, -96.92388889",
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewApp() {
    MyApplicationTheme {
        MyApp()
    }
}
