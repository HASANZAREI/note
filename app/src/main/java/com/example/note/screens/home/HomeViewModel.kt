package com.example.note.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.note.database.NoteEntity
import com.example.note.repositories.NoteRepositories
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeViewModel: ViewModel(), KoinComponent {
    private val repo:NoteRepositories by inject()

    private val _notes:MutableStateFlow<List<NoteEntity>> = MutableStateFlow(emptyList())
    val notes = _notes.asStateFlow()

    private val _searchedNotes:MutableStateFlow<List<NoteEntity>?> = MutableStateFlow(null)
    val searchedNotes = _searchedNotes.asStateFlow()

    init {
        getNotes()
    }

    private fun getNotes(){
        viewModelScope.launch(Dispatchers.IO) {
            repo.getAllNotes().collect{data->
                _notes.update { data }
            }
        }
    }

    fun deleteNote(note:NoteEntity){
        viewModelScope.launch(Dispatchers.IO){
            repo.deleteNote(note)
        }
    }

    fun addNote(note: NoteEntity){
        viewModelScope.launch(Dispatchers.IO){
            repo.addNote(note)
        }
    }

    fun searchNotes(query:String){
        if (query.isEmpty()){
            _searchedNotes.update { null }
        }else{
            _searchedNotes.update {
                _notes.value.filter { it.note.contains(query) }
            }
        }
    }

}