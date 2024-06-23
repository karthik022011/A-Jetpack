package com.karthik.room.features.note

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.kartheek.roomdatabase.presenter.notes.NoteViewModel
import com.karthik.room.commoncomposables.CircularProgressBar
import com.karthik.room.entity.Note
import com.karthik.room.features.note.composables.AddFloatingActionButton
import com.karthik.room.features.note.composables.AddNoteAlertDialog
import com.karthik.room.features.note.composables.NotesContentUI
import com.karthik.room.features.note.composables.NotesTopBar
import com.karthik.room.utils.NetworkResult
import com.karthik.room.utils.showToast


@Composable
@ExperimentalMaterialApi
fun NoteScreen(
    viewModel: NoteViewModel
){
    val noteState by viewModel.uiState.collectAsState()
    //using material not material3
    Scaffold(
        topBar = {
            NotesTopBar()
        },
        content = { padding ->
            NoteScreenContent(
                padding = padding,
                noteUiState = noteState
            )
            AddNoteAlertDialog(
                openDialog = viewModel.openDialog,
                closeDialog = {
                    viewModel.closeDialog()
                },
                addNote = { note ->
                    viewModel.addNote(note)
                }
            )
        },
        floatingActionButton = {
            AddFloatingActionButton(
                openDialog = {
                    viewModel.openDialog()
                }
            )
        }
    )
}

@ExperimentalMaterialApi
@Composable
private fun NoteScreenContent(
    padding: PaddingValues,
    noteUiState: NetworkResult<List<Note>>
) {
        when (noteUiState) {
            is NetworkResult.Loading -> {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    CircularProgressBar(
                        modifier = Modifier
                            .padding(top = 16.dp)
                    )
                }
            }
            is NetworkResult.Success -> {
                if (noteUiState.data != null) {
                    NotesContentUI(
                        padding = padding,
                        notes = noteUiState.data
                    )
                }
            }
            is NetworkResult.Error -> {
                val context = LocalContext.current
                context.showToast("Something went wrong, please try again.")
            }
        }
}