package com.example.note

import android.app.Application
import androidx.room.Room
import com.example.note.database.NoteDatabase
import com.example.note.repositories.NoteRepositories
import com.example.note.repositories.NoteRepositoriesImpl
import org.koin.core.context.startKoin
import org.koin.dsl.bind
import org.koin.dsl.module

class KoinApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(module {
                single {
                    Room
                        .databaseBuilder(this@KoinApp, NoteDatabase::class.java, "db")
                        .build()
                }
                single {
                    NoteRepositoriesImpl(database = get())
                } bind NoteRepositories::class
            }
            )
        }
    }
}