package com.example.krivo.book.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.krivo.book.R
import com.example.krivo.book.model.Book

@Composable
fun BookDetailsScreen(
    book: Book?,
    onBack: () -> Unit,
    onToggleRead: () -> Unit
) {
    if (book == null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Text(stringResource(R.string.book_not_found))
            TextButton(onClick = onBack) {
                Text(stringResource(R.string.back))
            }
        }
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        TextButton(onClick = onBack) {
            Text(stringResource(R.string.back))
        }

        Text(
            text = book.title,
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = book.author,
            style = MaterialTheme.typography.titleMedium
        )

        book.description?.let { Text(it) }

        Text(
            text = if (book.isRead) {
                stringResource(R.string.status_read)
            } else {
                stringResource(R.string.status_not_read)
            }
        )

        Button(onClick = onToggleRead) {
            Text(
                if (book.isRead) {
                    stringResource(R.string.mark_unread)
                } else {
                    stringResource(R.string.mark_read)
                }
            )
        }
    }
}
