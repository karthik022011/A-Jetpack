package com.karthik.room.features.note.repository


import com.karthik.room.room.NoteDatabase
import com.karthik.room.entity.Note
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepositoryImpl@Inject constructor(
    val appDatabase: NoteDatabase
) : NoteRepository {


    override fun getAllNotes():  Flow<List<Note>> {
      return  appDatabase.appDao().getAllNotes()
    }

    override suspend fun getNote(id: Int): Note {
        return  appDatabase.appDao().getNote(id)
    }

    override suspend fun addNote(note: Note) {
        appDatabase.appDao().insertNote(note)
    }

    override suspend fun updateNote(note: Note) {
        appDatabase.appDao().updateNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        appDatabase.appDao().deleteNote(note)
    }


}