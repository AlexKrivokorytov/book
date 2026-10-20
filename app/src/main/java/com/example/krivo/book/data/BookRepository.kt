package com.example.krivo.book.data

import com.example.krivo.book.data.local.BookDao
import com.example.krivo.book.model.Book
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BookRepository(
    private val dao: BookDao
) {
    fun observeBooks(): Flow<List<Book>> =
        dao.observeAll().map { entities ->
            entities.map { it.toBook() }
        }

    suspend fun addBook(book: Book) {
        dao.insert(book.toEntity())
    }

    suspend fun updateBook(book: Book) {
        dao.update(book.toEntity())
    }
}
