package com.projects.notes.data.dto

import com.projects.notes.model.Note
import com.projects.notes.data.data_source.entity.Note as NoteEntity

fun Note.toNoteEntity() = NoteEntity(
    title = this.title,
    body = this.body,
    createDate = this.createDate,
    colorNote = this.colorNote,
    idNota = this.idNota
)
