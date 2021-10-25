package com.nmrc.theme

import androidx.compose.ui.graphics.Color

sealed class Gradient(val start: Color, val end: Color) {

    object Solar : Gradient(Color(0xFFFF4E50), Color(0xFFF9D423))

    object Mantle : Gradient(Color(0xFF24C6DC), Color(0xFF514A9D))

    object Miaka : Gradient(Color(0xFFFC354C), Color(0xFF0ABFBC))

    fun getGradient(): List<Color> = listOf(start, end)
}
