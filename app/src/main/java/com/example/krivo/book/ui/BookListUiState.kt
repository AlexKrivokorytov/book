package com.example.krivo.book.ui

import com.example.krivo.book.model.Book

data class BookListUiState(
    val books: List<Book> = emptyList()
)
