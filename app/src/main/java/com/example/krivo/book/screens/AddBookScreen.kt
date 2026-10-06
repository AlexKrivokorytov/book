package com.example.krivo.book.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun AddBookScreen(
    onSave: (String, String, String?, Boolean) -> Unit,
    onCancel: () -> Unit
) {
    var title by rememberSaveable { mutableStateOf("") }
    var author by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }
    var isRead by rememberSaveable { mutableStateOf(false) }

    AddBookForm(
        modifier = Modifier.fillMaxSize(),
        title = title,
        onTitleChange = { title = it },
        author = author,
        onAuthorChange = { author = it },
        description = description,
        onDescriptionChange = { description = it },
        isRead = isRead,
        onIsReadChange = { isRead = it },
        onSave = {
            onSave(
                title.trim(),
                author.trim(),
                description.trim().takeIf { it.isNotEmpty() },
                isRead
            )
        },
        onCancel = onCancel
    )
}
