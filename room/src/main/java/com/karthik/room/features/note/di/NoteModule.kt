package com.karthik.room.features.note.di

import com.karthik.room.features.note.repository.NoteRepository
import com.karthik.room.features.note.repository.NoteRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
abstract class NoteModule {

    @Binds
    abstract fun bindNoteInterface(
        noteRepositoryImpl: NoteRepositoryImpl
    ): NoteRepository
}