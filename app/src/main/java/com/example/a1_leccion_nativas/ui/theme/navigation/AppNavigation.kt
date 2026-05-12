package com.example.a1_leccion_nativas.ui.theme.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import com.example.a1_leccion_nativas.data.Guitarra
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.a1_leccion_nativas.ui.theme.screens.ConsultaScreen
import com.example.a1_leccion_nativas.ui.theme.screens.HomeScreen
import com.example.a1_leccion_nativas.ui.theme.screens.IngresoScreen


@Composable
fun AppNavigation(
    guitarras: List<Guitarra>,
    onAgregarGuitarra: (Guitarra) -> Unit
) {
    var currentScreen by remember { mutableStateOf("home") }

    when (currentScreen) {
        "home" -> HomeScreen(
            onNavigateToIngreso = { currentScreen = "ingreso" },
            onNavigateToConsulta = { currentScreen = "consulta" }
        )
        "ingreso" -> IngresoScreen(
            onAgregar = { nueva ->
                onAgregarGuitarra(nueva)
                currentScreen = "consulta"
            },
            onCancel = { currentScreen = "home" }
        )
        "consulta" -> ConsultaScreen(
            guitarras = guitarras,
            onBack = { currentScreen = "home" }
        )
    }
}