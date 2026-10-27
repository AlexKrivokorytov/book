package com.example.krivo.book

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.krivo.book.navigation.AddBookRoute
import com.example.krivo.book.navigation.BookDetailsRoute
import com.example.krivo.book.navigation.BookListRoute
import com.example.krivo.book.screens.AddBookScreen
import com.example.krivo.book.screens.BookDetailsScreen
import com.example.krivo.book.screens.BookListScreen
import com.example.krivo.book.ui.BookViewModel

@Composable
fun BookTrackerApp() {
    val bookViewModel: BookViewModel = viewModel(factory = BookViewModel.Factory)

    val uiState by bookViewModel.uiState.collectAsStateWithLifecycle()
    val backStack = rememberNavBackStack(BookListRoute)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<BookListRoute> {
                BookListScreen(
                    books = uiState.books,
                    onBookClick = { bookId ->
                        backStack.add(BookDetailsRoute(bookId))
                    },
                    onAddBook = {
                        backStack.add(AddBookRoute)
                    }
                )
            }

            entry<AddBookRoute> {
                AddBookScreen(
                    onCancel = {
                        backStack.removeLastOrNull()
                    },
                    onSave = { title, author, description, isRead ->
                        bookViewModel.addBook(
                            title = title,
                            author = author,
                            description = description,
                            isRead = isRead
                        )
                        backStack.removeLastOrNull()
                    }
                )
            }

            entry<BookDetailsRoute> { route ->
                BookDetailsScreen(
                    book = uiState.books.find {
                        it.id == route.bookId
                    },
                    onBack = {
                        backStack.removeLastOrNull()
                    },
                    onToggleRead = {
                        bookViewModel.toggleBookRead(route.bookId)
                    }
                )
            }
        }
    )
}
