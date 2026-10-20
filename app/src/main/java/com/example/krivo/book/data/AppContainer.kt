package com.example.krivo.book.data

import android.content.Context
import androidx.room3.Room
import androidx.sqlite.driver.AndroidSQLiteDriver
import com.example.krivo.book.data.local.AppDatabase

class AppContainer(context: Context) {
    private val database: AppDatabase =
        Room.databaseBuilder<AppDatabase>(
            context = context.applicationContext,
            name = "book_tracker.db"
        )
            .setDriver(AndroidSQLiteDriver())
            .build()

    val bookRepository: BookRepository =
        BookRepository(database.bookDao())
}
