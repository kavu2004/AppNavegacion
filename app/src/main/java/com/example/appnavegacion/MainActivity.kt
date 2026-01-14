package com.example.appnavegacion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            AppNavegacion()
        }
    }
}

@Composable
fun AppNavegacion() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "contador"
    ) {

        // Primera pantalla
        composable("contador") {
            PantallaContador(navController)
        }

        // Segunda pantalla con parámetro
        composable("resultado/{valor}") { backStackEntry ->
            val valor = backStackEntry.arguments?.getString("valor") ?: "0"
            PantallaResultado(navController, valor)
        }

    }

}


@Composable
fun PantallaContador(navController: NavController) {

    var contador by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Contador: $contador",
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { contador++ }
        ) {
            Text("Sumar")
        }

        Button(
            onClick = { contador-- }
        ) {
            Text("Restar")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                // Envía el valor a la otra pantalla
                navController.navigate("resultado/$contador")
            }
        ) {
            Text("Ver Resultado")
        }

    }
}

@Composable
fun PantallaResultado(navController: NavController, valor: String) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Valor final: $valor",
            fontSize = 28.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                navController.popBackStack()
            }
        ) {
            Text("Regresar")
        }
    }
}

