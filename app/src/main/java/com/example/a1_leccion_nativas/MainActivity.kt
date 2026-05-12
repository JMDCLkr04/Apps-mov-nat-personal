package com.example.a1_leccion_nativas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.a1_leccion_nativas.data.DummyData
import com.example.a1_leccion_nativas.data.Guitarra
import com.example.a1_leccion_nativas.ui.theme._1leccionnativasTheme
import com.example.a1_leccion_nativas.ui.theme.navigation.AppNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _1leccionnativasTheme {
                // LLAMADA A LA FUNCIÓN PRINCIPAL
                MiApp()
            }
        }
    }
}

@Composable
fun MiApp() {
    // Convertimos a una lista inmutable inicial para evitar problemas de tipos
    // y asegurar que Compose detecte correctamente los cambios.
    var guitarras by remember {
        mutableStateOf<List<Guitarra>>(DummyData.getInitialGuitarras())
    }

    AppNavigation(
        guitarras = guitarras,
        onAgregarGuitarra = { nuevaGuitarra ->
            // Se crea una nueva lista con el elemento agregado, disparando la recomposición
            guitarras = guitarras + nuevaGuitarra
        }
    )
}