package com.projects.notes.model

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}

