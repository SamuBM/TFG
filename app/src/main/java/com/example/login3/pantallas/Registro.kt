package com.example.login3.pantallas

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

@Composable
fun mostrarRegistro(navController: NavController) {

    var nombre by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var contrasena2 by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }

    var contraseñasCoinciden by remember { mutableStateOf(true) }

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
            verticalArrangement = Arrangement.Center
        ) {
            TextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre") })
            Spacer(modifier = Modifier.height(20.dp))

            TextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                value = contrasena,
                onValueChange = {
                    contrasena = it
                },
                label = { Text("Contraseña") }
            )
            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                value = contrasena2,
                onValueChange = {
                    contrasena2 = it
                },
                label = { Text("Repetir Contraseña") }
            )
            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                value = telefono,
                onValueChange = { telefono = it },
                label = { Text("Teléfono") })
            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Blue)
                    .padding(end = 20.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = {
                        registrarUsuarioVolley(
                            contexto,
                            nombre,
                            email,
                            contrasena,
                            telefono,
                            navController
                        )
                    },
                    enabled = if (comprobarContraseñas(contrasena, contrasena2) && comprobarCampos(
                            nombre,
                            email,
                            contrasena,
                            contrasena2,
                            telefono
                        )
                    ) true else false,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (contraseñasCoinciden) Color.Green else Color.Gray
                    )
                ) {
                    Text(text = "FINALIZAR")

                }

                Spacer(modifier = Modifier.width(10.dp))

                Button(onClick = { navController.popBackStack() }) {
                    Text(text = "VOLVER")
                }
            }

            Text(
                text = if (contrasena == contrasena2) "" else "Las contraseñas tienen que coincidir",
                color = Color.Red,
                modifier = Modifier.padding(5.dp),
                fontSize = 15.sp
            )

            Text(
                text = if (nombre == "" || email == "" || contrasena == "" || contrasena2 == "" || telefono == "")
                    "No puede haber un campo vacio" else "",
                color = Color.Red,
                modifier = Modifier.padding(5.dp),
                fontSize = 15.sp
            )

        }
    }
}

fun registrarUsuarioVolley(
    context: Context,
    nombre: String,
    email: String,
    contrasena: String,
    telefono: String,
    navController: NavController
) {
    val url = "http://192.168.1.12/Candroid_mysql/registrar.php"

    val stringRequest = object : StringRequest(
        Request.Method.POST, url,
        Response.Listener { response ->
            if (response.trim() == "success") {
                Toast.makeText(context, "Registro exitoso", Toast.LENGTH_SHORT).show()
                navController.popBackStack()
            } else {
                Toast.makeText(context, "Error en el registro", Toast.LENGTH_SHORT).show()
            }
        },
        Response.ErrorListener { error ->
            Toast.makeText(context, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
        }
    ) {
        override fun getParams(): MutableMap<String, String> {
            val parametros = HashMap<String, String>()
            parametros["nombre"] = nombre
            parametros["email"] = email
            parametros["contraseña"] = contrasena
            parametros["telefono"] = telefono
            return parametros
        }
    }

    val requestQueue: RequestQueue = Volley.newRequestQueue(context)
    requestQueue.add(stringRequest)
}

fun comprobarContraseñas(contrasena: String, contrasena2: String): Boolean {

    if (contrasena == contrasena2) {
        return true;
    } else {
        return false;
    }
}

fun comprobarCampos(
    nombre: String,
    email: String,
    contrasena: String,
    contrasena2: String,
    telefono: String
): Boolean {

    if (nombre == "" || email == "" || contrasena == "" || contrasena2 == "" || telefono == "") {
        return false
    } else {
        return true
    }
}
