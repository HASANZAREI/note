package com.example.note.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Insert
    fun addNote(note: NoteEntity)

    @Query("SELECT * FROM `notes`")
    fun getAllNotes(): Flow<List<NoteEntity>>

    @Delete
    fun deleteNote(note: NoteEntity)
}