package com.example.krivo.book.data

import com.example.krivo.book.data.local.BookEntity
import com.example.krivo.book.model.Book

fun BookEntity.toBook(): Book = Book(
    id = id,
    title = title,
    author = author,
    description = description,
    isRead = isRead
)

fun Book.toEntity(): BookEntity = BookEntity(
    id = id,
    title = title,
    author = author,
    description = description,
    isRead = isRead
)
