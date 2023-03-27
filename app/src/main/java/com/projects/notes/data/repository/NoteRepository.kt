package com.projects.notes.data.repository

import com.projects.notes.data.data_source.dao.NoteDao
import com.projects.notes.data.dto.toNoteEntity
import com.projects.notes.model.*
import com.projects.notes.model.Result.Loading
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NoteRepository(
    private val noteDao: NoteDao
) {

    suspend fun getAllNotes(order: NoteOrder): Result<List<Note>> {
        return withContext(Dispatchers.IO) {
            kotlin.runCatching {
                val noteList = noteDao.getAllNote().map { Note(it) }.orderingList(order)
                Result.ResultSuccess(noteList)
            }.getOrElse {
                Result.ResultError(it)
            }
        }
    }

    suspend fun getNoteById(noteId: Int): Result<Note> {
        return withContext(Dispatchers.IO) {
            kotlin.runCatching {
                val note = noteDao.getNoteById(noteId)
                Result.ResultSuccess(successData = Note(note))
            }.getOrElse {
                Result.ResultError(it)
            }
        }

    }

    suspend fun addNote(note: Note): Result<Long> {
        return withContext(Dispatchers.IO) {
            kotlin.runCatching {
                Result.ResultSuccess(noteDao.addNote(note.toNoteEntity()))
            }.getOrElse {
                Result.ResultError(it)
            }
        }
    }

    suspend fun deleteNote(note: Note): Result<Boolean> {
        return withContext(Dispatchers.IO) {
            kotlin.runCatching {
                val deleteResult = noteDao.deleteNote(note.toNoteEntity())
                if (deleteResult == 1) {
                    Result.ResultSuccess(true)
                } else {
                    Result.ResultError(Throwable("Generic Error"))
                }
            }.getOrElse {
                Result.ResultError(it)
            }
        }
    }

}