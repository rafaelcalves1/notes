package com.projects.notes.data.data_source.entity

import androidx.annotation.ColorRes
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.random.Random

@Entity(tableName = "note")
data class Note(
    @ColumnInfo(name = "title_note") val title: String,
    @ColumnInfo(name = "body") val body: String,
    @ColumnInfo(name = "create_at") val createDate: Long,
    @ColorRes
    @ColumnInfo(name = "color") val colorNote: Int,
    @PrimaryKey
    @ColumnInfo(name = "note_id")
    val idNota: Int = Random.nextInt()
)