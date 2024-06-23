package com.karthik.room.features.editnote

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.kartheek.roomdatabase.presenter.editnote.NoteEditViewModel
import com.karthik.room.commoncomposables.CircularProgressBar
import com.karthik.room.entity.Note
import com.karthik.room.features.editnote.composables.NoteEditContentUI
import com.karthik.room.features.note.composables.NotesTopBar
import com.karthik.room.utils.MyAppConstants
import com.karthik.room.utils.NetworkResult
import com.karthik.room.utils.showToast


@Composable
@ExperimentalMaterialApi
fun NoteEditScreen(
    viewModel: NoteEditViewModel
){
    val noteState by viewModel.uiState.collectAsState()
    //using material not material3
    Scaffold(
        topBar = {
            NotesTopBar()
        },
        content = { padding ->
            NoteEditScreenContent(
                padding = padding,
                noteUiState = noteState
            ){
                viewModel.updateNote(it)
            }
        }
    )
}

@ExperimentalMaterialApi
@Composable
private fun NoteEditScreenContent(
    padding: PaddingValues,
    noteUiState: NetworkResult<Note>,
    onUpdateClick:(note: Note) -> Unit
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
                var title by remember { mutableStateOf(MyAppConstants.Empty_VALUE) }
                var description by remember { mutableStateOf(MyAppConstants.Empty_VALUE) }
                NoteEditContentUI(
                    padding = padding,
                    note = noteUiState.data,
                    updateTitle = {
                        title = it
                    },
                    updateDescription = {
                        description= it
                    },
                    updateNote = {
                        onUpdateClick(it)
                    }
                )
            }
        }
        is NetworkResult.Error -> {
            val context = LocalContext.current
            context.showToast("Something went wrong, please try again.")
        }
    }
}
