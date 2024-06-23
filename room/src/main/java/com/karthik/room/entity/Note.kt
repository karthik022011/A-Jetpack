package com.karthik.room.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.karthik.room.utils.MyAppConstants.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val description: String,
    @Embedded val emb:NoteEmbedded
)

