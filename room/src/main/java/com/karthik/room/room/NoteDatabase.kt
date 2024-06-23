package com.karthik.room.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.karthik.room.entity.Note
import com.karthik.room.utils.Converters


@Database(
    entities = [Note::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun appDao(): NoteDao
}