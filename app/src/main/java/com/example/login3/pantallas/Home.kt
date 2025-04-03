package com.example.login3.pantallas


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.login3.R

@Composable
fun mostrarHome(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Aquí va el contenido de la pantalla principal
        Column(
            modifier = Modifier.align(Alignment.Center) // Puedes poner lo que desees aquí
        ) {
            Text(
                text = "Pantalla principal",
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )

        }
        mostrarNavegador(navController , "Home")
    }
}

@Composable
fun mostrarNavegador(navController: NavController, selectedScreen: String) {
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val iconSize = if (screenWidth < 400) 50.dp else 60.dp // Reducción de tamaño de íconos en pantallas pequeñas

    Box(
        modifier = Modifier.fillMaxSize(), // Para que la barra de navegación se posicione en la parte inferior
        contentAlignment = Alignment.BottomCenter // Alinea el Row en la parte inferior
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            NavigationItem("Comidas", R.drawable.comidas, navController, selectedScreen, iconSize)
            NavigationItem("Ejercicios", R.drawable.ejercicios, navController, selectedScreen, iconSize)
            NavigationItem("Home", R.drawable.entrenar, navController, selectedScreen, iconSize)
            NavigationItem("Historial", R.drawable.historial, navController, selectedScreen, iconSize)
            NavigationItem("Perfil", R.drawable.perfil, navController, selectedScreen, iconSize)
        }
    }
}

@Composable
fun NavigationItem(
    screenName: String,
    iconRes: Int,
    navController: NavController,
    selectedScreen: String,
    iconSize: Dp
) {
    val isSelected = screenName == selectedScreen
    val backgroundColor = if (isSelected) Color(0xFFD3D3D3) else Color.Transparent // Gris claro si está seleccionado

    Box(
        modifier = Modifier
            .size(iconSize)
            .background(color = backgroundColor, shape = RoundedCornerShape(10.dp))
            .clickable {
                navController.navigate(screenName)
            },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            modifier = Modifier.size(iconSize * 0.85f) // Ajustamos el tamaño de la imagen dentro del ícono
        )
    }
}






