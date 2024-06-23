package com.kartheek.roomdatabase.presenter.notes

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
class NoteViewModel @Inject constructor(
    private val noteRepository: NoteRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<NetworkResult<List<Note>>>(NetworkResult.Loading())

    val uiState: StateFlow<NetworkResult<List<Note>>> = _uiState

    var openDialog by mutableStateOf(false)


   fun getAllNotes(){
       viewModelScope.launch {
           noteRepository.getAllNotes()
               .flowOn(Dispatchers.IO)
               .catch { e ->
                   // handle exception
                   _uiState.value = NetworkResult.Error(e.toString())
               }
               .collect {
                   // list of users from the network
                   _uiState.value = NetworkResult.Success(it)
               }
       }
   }

    fun addNote(note: Note){
        viewModelScope.launch {
            noteRepository.addNote(note)
        }
    }

    fun openDialog() {
        openDialog = true
    }

    fun closeDialog() {
        openDialog = false
    }

}