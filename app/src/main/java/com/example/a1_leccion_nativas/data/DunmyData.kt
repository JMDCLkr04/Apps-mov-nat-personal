package com.example.a1_leccion_nativas.data

import com.example.a1_leccion_nativas.R

object DummyData {
    fun getInitialGuitarras(): MutableList<Guitarra> = mutableListOf(
        Guitarra(1, "Fender", "Telecaster", "Electrica", 300.00, R.drawable.fender_telecaster),
        Guitarra(2, "Ibanez", "GA5TCE", "Acústica-Nylon",420.50, R.drawable.ibanez_ga5tce),
        Guitarra(3, "Gibson", "SG", "Eléctrica", 780.35, R.drawable.gibson_sg)
    )
}