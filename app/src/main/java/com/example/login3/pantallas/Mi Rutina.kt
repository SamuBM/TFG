package com.example.login3.pantallas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun mostrarMiRutina(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth().height(100.dp),
            color = Color.DarkGray
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .padding(top = 15.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val secciones = listOf(
                    "Home",
                    "Videos",
                    "Mi Rutina",
                    "Mapa",
                )

                secciones.forEach { seccion ->
                    TextButton(
                        onClick = { navController.navigate(seccion) },
                        colors = ButtonDefaults.textButtonColors(
                            containerColor = Color.Gray, // Fondo gris
                            contentColor = Color.White   // Texto blanco
                        ),
                        modifier = Modifier.padding(5.dp)
                    ) {
                        Text(seccion, color = Color.White)
                    }
                }
            }
        }
    }
}


