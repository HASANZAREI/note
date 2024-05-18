package com.example.note.repositories

import com.example.note.database.NoteDatabase
import com.example.note.database.NoteEntity
import kotlinx.coroutines.flow.Flow

class NoteRepositoriesImpl(
    private val database: NoteDatabase
): NoteRepositories {
    private val dao = database.noteDao()

    override suspend fun addNote(note: NoteEntity) = dao.addNote(note =  note)
    override suspend fun deleteNote(note: NoteEntity) = dao.deleteNote(note =  note)
    override suspend fun getAllNotes(): Flow<List<NoteEntity>> = dao.getAllNotes()
}