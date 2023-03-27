package com.projects.notes

import android.app.Application
import com.projects.notes.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NotesApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val moduleList = listOf(
            AppModule.dataBaseModule,
            AppModule.respositoryModule,
            AppModule.viewModelModule
        )

        startKoin {
            androidContext(this@NotesApp)
            modules(moduleList)
        }
    }

}