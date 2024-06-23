package com.kartheek.roomdatabase.presenter.editnote

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kartheek.roomdatabase.core.NetworkResult
import com.kartheek.roomdatabase.data.model.Note
import com.kartheek.roomdatabase.data.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteEditViewModel @Inject constructor(
    private val noteRepository: NoteRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<NetworkResult<Note>>(NetworkResult.Loading())

    val uiState: StateFlow<NetworkResult<Note>> = _uiState


   fun getNoteById(noteId:String){
       viewModelScope.launch {
           try {
               noteRepository.getNote(noteId.toInt()).let {
                   _uiState.value = NetworkResult.Success(it)
               }
           } catch (e: Exception) {
               _uiState.value = NetworkResult.Error(e.toString())
           }
       }
   }

    fun updateNote(note:Note){
       viewModelScope.launch {
           try {
               noteRepository.updateNote(note)
           } catch (e: Exception) {
               _uiState.value = NetworkResult.Error(e.toString())
           }
       }
   }
}