package com.example.a1_leccion_nativas.ui.theme.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a1_leccion_nativas.data.Guitarra
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import com.example.a1_leccion_nativas.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IngresoScreen(
    onAgregar: (Guitarra) -> Unit,
    onCancel: () -> Unit
) {
    // Estados locales del formulario corregidos para Guitarra
    var marca by remember { mutableStateOf("") }
    var modelo by remember { mutableStateOf("") }
    var tipo by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    
    // Estado para mostrar errores
    var mostrarError by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Registrar Nueva Guitarra") },
                navigationIcon = {
                    IconButton(onClick = onCancel) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Tarjeta del formulario
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = "Datos de la Guitarra",
                        fontSize = 20.sp,
                        style = MaterialTheme.typography.titleLarge
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Campo: Marca
                    OutlinedTextField(
                        value = marca,
                        onValueChange = { marca = it },
                        label = { Text("Marca *") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        isError = mostrarError && marca.isBlank()
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Campo: Modelo
                    OutlinedTextField(
                        value = modelo,
                        onValueChange = { modelo = it },
                        label = { Text("Modelo *") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        isError = mostrarError && modelo.isBlank()
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Campo: Tipo
                    OutlinedTextField(
                        value = tipo,
                        onValueChange = { tipo = it },
                        label = { Text("Tipo (Acústica, Eléctrica...) *") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        isError = mostrarError && tipo.isBlank()
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Campo: Precio
                    OutlinedTextField(
                        value = precio,
                        onValueChange = { precio = it },
                        label = { Text("Precio (USD) *") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                        singleLine = true,
                        isError = mostrarError && precio.isBlank()
                    )

                    // Mensaje de error general
                    if (mostrarError) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "⚠️ Completa todos los campos obligatorios (*)",
                            color = MaterialTheme.colorScheme.error,
                            fontSize = 12.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Botones: Guardar y Limpiar
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Button(
                            onClick = {
                                // Validar campos obligatorios
                                if (marca.isNotBlank() && modelo.isNotBlank() && tipo.isNotBlank() && precio.isNotBlank()) {
                                    val precioDouble = precio.toDoubleOrNull() ?: 0.0
                                    val nuevaGuitarra = Guitarra(
                                        id = (System.currentTimeMillis() % Int.MAX_VALUE).toInt(),
                                        marca = marca,
                                        modelo = modelo,
                                        tipo = tipo,
                                        precio = precioDouble,
                                        ImagenUrl = R.drawable.ic_launcher_foreground // Usamos uno por defecto del sistema
                                    )
                                    onAgregar(nuevaGuitarra)
                                    mostrarError = false
                                } else {
                                    mostrarError = true
                                }
                            },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = "Guardar"
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Guardar")
                        }

                        OutlinedButton(
                            onClick = {
                                // Limpiar campos
                                marca = ""
                                modelo = ""
                                tipo = ""
                                precio = ""
                                mostrarError = false
                            },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text("Limpiar")
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Botón cancelar (volver sin guardar)
                    TextButton(
                        onClick = onCancel,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Cancelar")
                    }
                }
            }
        }
    }
}

// Preview
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun IngresoScreenPreview() {
    MaterialTheme {
        IngresoScreen(
            onAgregar = {},
            onCancel = {}
        )
    }
}
