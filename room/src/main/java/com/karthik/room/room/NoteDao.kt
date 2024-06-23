package com.karthik.room.room

import androidx.room.*
import com.karthik.room.entity.Note
import com.karthik.room.utils.MyAppConstants.TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

   //To get all registered users
    @Query("SELECT * FROM Note")
     fun getAllNotes(): Flow<List<Note>>

    @Query("SELECT * FROM $TABLE_NAME WHERE id = :id")
    suspend fun getNote(id: Int): Note

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

}