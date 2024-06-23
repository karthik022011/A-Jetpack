package com.karthik.room.utils

import android.content.Context
import androidx.room.Room
import com.karthik.room.room.NoteDao
import com.karthik.room.room.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

 /*Now we are telling hilt to provide instance of database*/
 @Provides
 @Singleton
 fun provideAppDatabase(@ApplicationContext appContext: Context): NoteDatabase {
     return  Room.databaseBuilder(
           appContext,
           NoteDatabase::class.java,
         MyAppConstants.DATABASE_NAME
     ).build()
 }

 /*Now we are telling hilt to provide instance of dao*/
 @Provides
 fun provideAppDatabaseDao(appDatabase: NoteDatabase): NoteDao {
  return appDatabase.appDao()
 }




}