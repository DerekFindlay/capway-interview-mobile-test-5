package com.example.capwayscreen.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

//Placeholder Content Screen
@Composable
fun MoreScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Magenta),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "MORE${System.lineSeparator()}" +
                    "Content${System.lineSeparator()}Here",
            fontSize = MaterialTheme.typography.h3.fontSize,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
@Preview
fun MoreScreenPreview() {
    MoreScreen()
}