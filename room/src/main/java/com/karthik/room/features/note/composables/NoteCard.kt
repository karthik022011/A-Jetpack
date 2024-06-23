package com.karthik.room.features.note.composables

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.karthik.room.entity.Note
import com.karthik.room.features.editnote.NoteEditActivity

@Composable
@ExperimentalMaterialApi
fun NoteCard(
    note: Note
) {
    val mContext = LocalContext.current
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(
                start = 8.dp,
                end = 8.dp,
                top = 4.dp,
                bottom = 4.dp
            )
            .fillMaxWidth(),
        elevation = 3.dp,
        onClick = {
            val intent = Intent(mContext, NoteEditActivity::class.java)
            intent.putExtra("NoteID",note.id.toString())
            mContext.startActivity(intent)
        }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column {
                TextTitle(
                    noteTitle = note.title
                )
                TextDescription(
                    bookAuthor = note.description
                )
            }
            Spacer(
                modifier = Modifier.weight(1f)
            )
        }
    }
}