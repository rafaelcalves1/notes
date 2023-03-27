package com.projects.notes.di

import androidx.room.Room
import com.projects.notes.data.data_source.NoteDataBase
import com.projects.notes.data.data_source.NoteDataBase.Companion.DATABASE_NOME
import com.projects.notes.data.data_source.dao.NoteDao
import com.projects.notes.data.data_source.dao.NoteDao_Impl
import com.projects.notes.data.repository.NoteRepository
import com.projects.notes.ui.notelist.NoteListViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModule {

    val dataBaseModule = module {
        single {
            Room.databaseBuilder(
                androidApplication(),
                NoteDataBase::class.java,
                DATABASE_NOME
            ).fallbackToDestructiveMigration().build()
        }

    }

    val respositoryModule = module {

        fun provideNoteDao(dataBase: NoteDataBase): NoteDao{
            return dataBase.noteDao
        }
        single {
            NoteRepository(provideNoteDao(get()))
        }
    }

    val viewModelModule = module {
        viewModel {
            NoteListViewModel(get())
        }
    }

}