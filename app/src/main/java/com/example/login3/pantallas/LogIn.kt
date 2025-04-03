package com.example.login3.pantallas

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.InspectableModifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

@Composable
fun mostrarLogIn(navController: NavController) {

    var texto1 by remember { mutableStateOf("") }
    var texto2 by remember { mutableStateOf("") }
    var mensajeError by remember { mutableStateOf("") }

    val contexto = LocalContext.current

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .width(300.dp)
                .background(Color.Gray),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            TextField(
                value = texto1,
                onValueChange = { texto1 = it },
                label = { Text(text = "Correo") }
            )

            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                value = texto2,
                onValueChange = { texto2 = it },
                label = { Text(text = "Contraseña") }
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = mensajeError,
                color = Color.Red,
                modifier = Modifier.padding(8.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Blue),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(onClick = {
                    validarUsuario(
                        navController = navController,
                        email = texto1,
                        contraseña = texto2,
                        contexto = contexto,
                        actualizarMensaje = { mensajeError = it }
                    )
                }) {
                    Text(text = "ENTRAR")
                }

                Spacer(modifier = Modifier.width(10.dp))

                Button(onClick = {navController.navigate("Registro")}) {
                    Text(text = "REGISTRARSE")
                }
            }
        }
    }
}

fun validarUsuario(
    navController: NavController,
    email: String,
    contraseña: String,
    contexto: Context,
    actualizarMensaje: (String) -> Unit
) {
    val url = "http://192.168.1.12/Candroid_mysql/conexion.php"

    val queue = Volley.newRequestQueue(contexto)
    val resultadoPost = object : StringRequest(
        Request.Method.POST, url,
        Response.Listener<String> { response ->
            if (response.trim() == "success") {
                actualizarMensaje("Inicio de sesión exitoso")
                navController.navigate("Pantalla1")
            } else {
                actualizarMensaje("Usuario o contraseña incorrectos")
            }
        },
        Response.ErrorListener { error ->
            actualizarMensaje("Error en la conexión: ${error.message}")
        }
    ) {
        override fun getParams(): MutableMap<String, String> {
            val parametros = HashMap<String, String>()
            parametros["email"] = email
            parametros["contraseña"] = contraseña
            return parametros
        }
    }
    queue.add(resultadoPost)
}

