package com.projects.notes.model

import androidx.annotation.ColorRes
import com.projects.notes.data.data_source.entity.Note
import com.projects.notes.model.Note as NoteModel

data class Note(
    val title: String,
    val body: String,
    val createDate: Long,
    @ColorRes
    val colorNote: Int,
    val idNota: Int
) {
    constructor(response: Note) : this(
        title = response.title,
        body = response.body,
        createDate = response.createDate,
        colorNote = response.colorNote,
        idNota = response.idNota
    )
}

fun List<NoteModel>.orderingList(order: NoteOrder): List<NoteModel> {
    if(this.isEmpty()) return emptyList()
    return when(order.orderType){
        is OrderType.Ascending -> {
            when(order){
                is NoteOrder.Title -> this.sortedBy { it.title.lowercase() }
                is NoteOrder.Date -> this.sortedBy { it.createDate }
            }
        }
        is OrderType.Descending -> {
            when(order) {
                is NoteOrder.Date -> this.sortedBy { it.createDate }
                is NoteOrder.Title -> this.sortedBy { it.title.lowercase() }
            }
        }
    }
}

