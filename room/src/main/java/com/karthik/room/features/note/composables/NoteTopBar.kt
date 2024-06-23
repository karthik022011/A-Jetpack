package com.karthik.room.features.note.composables

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.karthik.room.R

@Composable
fun NotesTopBar() {
    TopAppBar (
        title = {
            Text(
                text = stringResource(R.string.notes)
            )
        }
    )
}