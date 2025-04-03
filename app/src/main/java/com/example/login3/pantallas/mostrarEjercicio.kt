package com.example.login3.pantallas

import android.net.Uri
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController

@Composable
fun mostrarEjercicios(navController: NavController, seccion: String) {
    val ejercicios = obtenerEjerciciosPorSeccion(seccion)

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "Ejercicios de $seccion", fontSize = 24.sp, color = Color.Black)

            ejercicios.forEach { (grupoMuscular, videos) ->
                Text(
                    text = grupoMuscular, fontSize = 20.sp, color = Color.DarkGray,
                    modifier = Modifier.padding(20.dp)
                )
                LazyRow {
                    items(videos) { videoUrl ->
                        VideoItem(videoUrl)
                    }
                }
            }

            Spacer(modifier = Modifier.height(15.dp))

            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End){
                Button(modifier = Modifier.padding(end = 20.dp),
                    onClick = {navController.popBackStack()}) {
                    Text(text = "VOLVER")
                }
            }

        }
    }
}

@Composable
fun VideoItem(videoUrl: String) {
    var showDialog by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .padding(8.dp)
            .size(150.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color.LightGray)
            .clickable { showDialog = true },
        contentAlignment = Alignment.Center
    ) {
        Text(text = "🎥 Video", fontSize = 16.sp, color = Color.Black)
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                Button(onClick = { showDialog = false }) {
                    Text("Cerrar")
                }
            },
            text = {
                WebViewComposable(videoUrl)
            }
        )
    }
}

@Composable
fun WebViewComposable(url: String) {
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                webViewClient = WebViewClient()
                settings.javaScriptEnabled = true
                loadUrl(url)
            }
        },
        modifier = Modifier.fillMaxWidth().fillMaxHeight()
    )
}

fun obtenerEjerciciosPorSeccion(seccion: String): Map<String, List<String>> {
    return when (seccion) {
        "Tren Superior" -> mapOf(
            "Ejercicios de Pecho" to listOf(
                "https://youtube.com/shorts/qz6jYEjE7ow?si=erXEjlRe8fjKnDo5",
                "https://www.youtube.com/watch?v=2"
            ),
            "Ejercicios de Hombro" to listOf(
                "https://www.youtube.com/watch?v=3",
                "https://www.youtube.com/watch?v=4"
            ),
            "Ejercicios de Espalda" to listOf(
                "https://www.youtube.com/watch?v=5",
                "https://www.youtube.com/watch?v=6"
            )
        )
        "Tren Inferior" -> mapOf(
            "Ejercicios de Piernas" to listOf(
                "https://www.youtube.com/watch?v=7",
                "https://www.youtube.com/watch?v=8"
            ),
            "Ejercicios de Glúteos" to listOf(
                "https://www.youtube.com/watch?v=9",
                "https://www.youtube.com/watch?v=10"
            )
        )
        "Cardio" -> mapOf(
            "Ejercicios de Resistencia" to listOf(
                "https://www.youtube.com/watch?v=11",
                "https://www.youtube.com/watch?v=12"
            ),
            "Ejercicios HIIT" to listOf(
                "https://www.youtube.com/watch?v=13",
                "https://www.youtube.com/watch?v=14"
            )
        )
        "Estiramientos" -> mapOf(
            "Estiramientos de Piernas" to listOf(
                "https://www.youtube.com/watch?v=15",
                "https://www.youtube.com/watch?v=16"
            ),
            "Estiramientos de Espalda" to listOf(
                "https://www.youtube.com/watch?v=17",
                "https://www.youtube.com/watch?v=18"
            )
        )
        else -> emptyMap()
    }
}
