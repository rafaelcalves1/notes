package com.projects.notes.ui.notelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projects.notes.data.repository.NoteRepository
import com.projects.notes.model.Result
import com.projects.notes.model.NoteOrder
import com.projects.notes.model.Note
import com.projects.notes.model.OrderType
import com.projects.notes.model.Result.*
import kotlinx.coroutines.launch

class NoteListViewModel(
    private val repository: NoteRepository
) : ViewModel() {

    private val _listNotes = MutableLiveData<Result<List<Note>>>()
    val listNotes: LiveData<Result<List<Note>>>
        get() = _listNotes


    init {
        getNotes()
    }

    fun getNotes(
        noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending)
    ) {
        viewModelScope.launch {
            try {
                _listNotes.postValue(Loading)
                when (val response = repository.getAllNotes(noteOrder)) {
                    is ResultSuccess -> {
                        _listNotes.postValue(ResultSuccess(successData = response.successData))
                    }
                    is ResultError -> {
                        _listNotes.postValue(ResultError(response.throwable))
                    }
                    is Loading ->{
                        _listNotes.postValue(Loading)
                    }
                }
            } catch (exception: Exception) {
                _listNotes.postValue(ResultError(exception))
            }
        }
    }
}

