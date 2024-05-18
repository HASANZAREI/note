package com.example.note.repositories

import com.example.note.database.NoteEntity
import kotlinx.coroutines.flow.Flow

interface NoteRepositories {
    suspend fun addNote(note: NoteEntity)
    suspend fun deleteNote(note: NoteEntity)
    suspend fun getAllNotes(): Flow<List<NoteEntity>>
}