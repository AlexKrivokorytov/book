package com.example.krivo.book.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.krivo.book.R
import com.example.krivo.book.components.BookCard
import com.example.krivo.book.model.Book

@Composable
fun BookList(
    books: List<Book>,
    modifier: Modifier = Modifier
) {
    if (books.isEmpty()) {
        Text(
            text = stringResource(R.string.empty_books),
            modifier = modifier.padding(16.dp)
        )
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                items = books,
                key = { book -> book.id }
            ) { book ->
                BookCard(book = book)
            }
        }
    }
}
