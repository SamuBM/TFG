package com.example.login3.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.NavController
import com.example.login3.R

@Composable
fun mostrarComidas(navController: NavController) {
    mostrarNavegador(navController, "Comidas")

    // Obtiene la configuración de la pantalla
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val density = LocalDensity.current.density

    // Ajuste dinámico de los tamaños
    val padding = 16.dp // Compose se encarga de convertir esto en función de la densidad
    val textSize = if (screenWidth < 400) 16.sp else 20.sp // Reducimos el tamaño de texto en pantallas más pequeñas

    // Ajustamos el LazyColumn para que no tape el navegador inferior
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight() // Esto hará que ocupe todo el espacio disponible pero sin sobrepasar
            .padding(start = padding, top = padding, end = padding, bottom = 80.dp), // Ajuste dinámico del padding
        verticalArrangement = Arrangement.spacedBy(16.dp) // Espacio entre los elementos
    ) {
        item {
            Text("DESAYUNOS", modifier = Modifier.padding(8.dp), fontSize = textSize)
            LazyRow {
                itemsIndexed(ImagenesPlatos.subList(14, 21)) { index, image ->
                    ListaItem(image, titulosPlatos[index + 14], screenWidth)
                }
            }
        }

        item {
            Text("COMIDAS", modifier = Modifier.padding(8.dp), fontSize = textSize)
            LazyRow {
                itemsIndexed(ImagenesPlatos.subList(7, 14)) { index, image ->
                    ListaItem(image, titulosPlatos[index + 7], screenWidth)
                }
            }
        }

        item {
            Text("CENAS", modifier = Modifier.padding(8.dp), fontSize = textSize)
            LazyRow {
                itemsIndexed(ImagenesPlatos.subList(0, 7)) { index, image ->
                    ListaItem(image, titulosPlatos[index], screenWidth)
                }
            }
        }
    }
}


@Composable
fun ListaItem(image: Int, title: String, screenWidth: Int) {
    // Calculamos el tamaño de la imagen y el título en función del tamaño de la pantalla
    val imageSize = if (screenWidth < 400) 100.dp else 140.dp // Reducción en pantallas pequeñas
    val titleWidth = if (screenWidth < 400) 100.dp else 120.dp

    Column(modifier = Modifier.padding(8.dp)) {
        Image(
            painter = painterResource(id = image),
            contentDescription = title,
            modifier = Modifier.size(imageSize)
        )
        Text(
            title,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .width(titleWidth)
                .padding(top = 4.dp)
        )
    }
}



val ImagenesPlatos = listOf(
        R.drawable.cena_brochetas_pollo_verduras,
        R.drawable.cena_ensalada_cesar_pollo_crujiente,
        R.drawable.cena_huevos_patatas_asadas_espinacas,
        R.drawable.cena_pollo_barbacoa_brocoli_patatas_asadas,
        R.drawable.cena_pollo_judias_verdes,
        R.drawable.cena_salmon_horno_esparragos,
        R.drawable.cena_wrap_salmon_pepino_queso_untar,
        R.drawable.comida_arroz_gambas_brocoli,
        R.drawable.comida_arroz_garbanzos_salsa_curry_pan_naan,
        R.drawable.comida_arroz_pollo_brocoli_sesamo,
        R.drawable.comida_ensalada_pasta,
        R.drawable.comida_noodles_pollo_verduras,
        R.drawable.comida_pasta_champinones_espinacas,
        R.drawable.comida_pimientos_rellenos,
        R.drawable.desayuno_tostadas_huevos_duros_guacamole_pesto,
        R.drawable.desayuno_tortilla_francesa_cherrys_tostadas_guacamole,
        R.drawable.desayuno_tortilla_espinacas_cherrys,
        R.drawable.desayuno_tostadas_guacamole_champinones,
        R.drawable.desayuno_tostadas_huevos_duros_guacamole_pesto,
        R.drawable.desayuno_wrap_tortilla_francesa_verduras,
        R.drawable.desayuno_yogur_avena_fruta
    )

    val titulosPlatos = listOf(
        "Brochetas de pollo y verduras",
        "Ensalada César con pollo crujiente",
        "Huevos con patatas asadas y espinacas",
        "Pollo a la barbacoa con brócoli y patatas asadas",
        "Pollo con judías verdes",
        "Salmón al horno con espárragos",
        "Wrap de salmón con pepino y queso de untar",
        "Arroz con gambas y brócoli",
        "Arroz con garbanzos y salsa curry acompañado de pan naan",
        "Arroz con pollo, brócoli y sésamo",
        "Ensalada de pasta",
        "Noodles con pollo y verduras",
        "Pasta con champiñones y espinacas",
        "Pimientos rellenos",
        "Tostadas con huevos duros, guacamole y pesto",
        "Tortilla francesa con cherrys y tostadas con guacamole",
        "Tortilla de espinacas con cherrys",
        "Tostadas con guacamole y champiñones",
        "Tostadas con huevos duros, guacamole y pesto",
        "Wrap con tortilla francesa y verduras",
        "Yogur con avena y fruta"
    )



