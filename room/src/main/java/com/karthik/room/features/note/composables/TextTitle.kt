package com.karthik.room.features.note.composables

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun TextTitle(
    noteTitle: String
) {
    Text(
        text = noteTitle,
        color = Color.DarkGray,
        fontSize = 25.sp
    )
}