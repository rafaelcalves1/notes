package com.projects.notes.data.data_source.dao

import androidx.room.*
import com.projects.notes.data.data_source.entity.Note

@Dao
interface NoteDao {

    @Query("SELECT * FROM note")
    suspend fun getAllNote(): List<Note>

    @Query("SELECT * FROM note WHERE note_id = :id")
    suspend fun getNoteById(id: Int): Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNote(nota: Note) : Long

    @Delete
    suspend fun deleteNote(nota: Note): Int
}