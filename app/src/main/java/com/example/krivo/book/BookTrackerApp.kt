package com.example.krivo.book

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.krivo.book.model.Book
import com.example.krivo.book.navigation.AddBookRoute
import com.example.krivo.book.navigation.BookDetailsRoute
import com.example.krivo.book.navigation.BookListRoute
import com.example.krivo.book.screens.AddBookScreen
import com.example.krivo.book.screens.BookDetailsScreen
import com.example.krivo.book.screens.BookListScreen

@Composable
fun BookTrackerApp() {
    var books by remember {
        mutableStateOf(
            listOf(
                Book(
                    id = 1,
                    title = "Clean Code",
                    author = "Robert C. Martin",
                    description = "A handbook of agile software craftsmanship.",
                    isRead = true
                ),
                Book(
                    id = 2,
                    title = "Kotlin in Action",
                    author = "Dmitry Jemerov, Svetlana Isakova",
                    description = null,
                    isRead = false
                ),
                Book(
                    id = 3,
                    title = "Refactoring",
                    author = "Martin Fowler",
                    description = "Improving the design of existing code.",
                    isRead = false
                )
            )
        )
    }

    val backStack = rememberNavBackStack(BookListRoute)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<BookListRoute> {
                BookListScreen(
                    books = books,
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
                        val nextId = (books.maxOfOrNull { it.id } ?: 0) + 1

                        books = books + Book(
                            id = nextId,
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
                    book = books.find { it.id == route.bookId },
                    onBack = {
                        backStack.removeLastOrNull()
                    },
                    onToggleRead = {
                        books = books.map { book ->
                            if (book.id == route.bookId) {
                                book.copy(isRead = !book.isRead)
                            } else {
                                book
                            }
                        }
                    }
                )
            }
        }
    )
}