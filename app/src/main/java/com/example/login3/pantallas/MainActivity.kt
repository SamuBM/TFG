package com.example.login3.pantallas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            iniciar()
        }
    }
}
@Preview( showBackground = true)
@Composable
fun iniciar(){

    var navController = rememberNavController();

    NavHost(navController = navController, startDestination = "Comidas"){
        composable("LogIn"){ mostrarLogIn(navController)}
        composable("Home"){ mostrarHome(navController)}
        /*composable("Ejercicios/{seccion}") { backStackEntry ->
            val seccion = backStackEntry.arguments?.getString("seccion") ?: "Desconocido"
            mostrarEjercicios(navController, seccion)
        }*/
        composable("Registro") { mostrarRegistro(navController) }
       // composable("Videos") { mostrarVideos(navController) }
       // composable("Mi Rutina") { mostrarMiRutina(navController) }
        composable("Comidas"){ mostrarComidas(navController) }
    }
}