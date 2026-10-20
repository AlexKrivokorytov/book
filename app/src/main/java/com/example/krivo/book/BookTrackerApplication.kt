package com.example.krivo.book

import android.app.Application
import com.example.krivo.book.data.AppContainer

class BookTrackerApplication : Application() {
    val container: AppContainer by lazy {
        AppContainer(this)
    }
}
