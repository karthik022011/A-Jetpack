package com.karthik.room.features.editnote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.kartheek.roomdatabase.presenter.editnote.NoteEditViewModel
import com.karthik.room.ui.theme.AJetpackTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteEditActivity : ComponentActivity()  {

    private val viewModel: NoteEditViewModel by viewModels()

    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AJetpackTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    NoteEditScreen(viewModel)
                }
            }
        }

        intent.getStringExtra("NoteID")?.let { viewModel.getNoteById(it) }
    }
}