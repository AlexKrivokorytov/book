package com.example.krivo.book.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.krivo.book.data.BookRepository
import com.example.krivo.book.model.Book
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class BookViewModel(
    private val repository: BookRepository
) : ViewModel() {
    val uiState: StateFlow<BookListUiState> = repository
        .observeBooks()
        .map { BookListUiState(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = BookListUiState()
        )

    fun addBook(
        title: String,
        author: String,
        description: String?,
        isRead: Boolean
    ) {
        viewModelScope.launch {
            repository.addBook(
                Book(
                    id = 0,
                    title = title,
                    author = author,
                    description = description,
                    isRead = isRead
                )
            )
        }
    }

    fun toggleBookRead(bookId: Int) {
        viewModelScope.launch {
            val book = uiState.value.books
                .firstOrNull { it.id == bookId }
                ?: return@launch

            repository.updateBook(
                book.copy(isRead = !book.isRead)
            )
        }
    }

    class Factory(
        private val repository: BookRepository
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(
            modelClass: Class<T>
        ): T {
            return BookViewModel(repository) as T
        }
    }
}
