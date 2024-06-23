package com.karthik.room.features.editnote.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.karthik.room.R
import com.karthik.room.entity.Note

@Composable
@ExperimentalMaterialApi
fun NoteEditContentUI(
    padding: PaddingValues,
    note: Note,
    updateTitle: (title: String) -> Unit,
    updateDescription: (description: String) -> Unit,
    updateNote: (note: Note) -> Unit,
) {

    Column(
        modifier = Modifier.fillMaxSize().padding(padding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = note.title,
            onValueChange = { title ->
                updateTitle(title)
            },
            placeholder = {
                Text(
                    text = stringResource(R.string.enter_title)
                )
            }
        )
        Spacer(
            modifier = Modifier.height(8.dp)
        )
        TextField(
            value = note.description,
            onValueChange = {
                updateDescription(it)
            },
            placeholder = {
                Text(
                    text = stringResource(R.string.enter_description)
                )
            }
        )
        Button(
            onClick = {
                updateNote(note)
            }
        ) {
            Text(
                text = stringResource(R.string.update)
            )
        }
    }
}