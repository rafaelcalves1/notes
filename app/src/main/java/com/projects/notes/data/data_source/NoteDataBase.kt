package com.projects.notes.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.projects.notes.data.data_source.dao.NoteDao
import com.projects.notes.data.data_source.entity.Note


@Database(
    entities = [Note::class],
    version = 1
)
abstract class NoteDataBase : RoomDatabase() {

    abstract val noteDao: NoteDao

    companion object {
        const val DATABASE_NOME = "note_db"
    }

}