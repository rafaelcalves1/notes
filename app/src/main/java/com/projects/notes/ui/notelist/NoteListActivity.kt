package com.projects.notes.ui.notelist

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.projects.notes.databinding.NotesListActivityBinding
import com.projects.notes.model.Note
import com.projects.notes.model.Result
import org.koin.android.ext.android.inject

class NoteListActivity : AppCompatActivity() {

    private lateinit var binding: NotesListActivityBinding

    // region Binding layout views
    private val recyclerView: RecyclerView
        get() = binding.recyclerView

    private val toolbar: Toolbar
        get() = binding.toolbar

    private val fabBtn: FloatingActionButton
        get() = binding.floatingActionButton

    private val refreshLayout: SwipeRefreshLayout
        get() = binding.refreshLayout
    // endregion

    private val viewModel: NoteListViewModel by inject()
    private val adapter: NoteListAdapter = NoteListAdapter(emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = NotesListActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        addListeners()
        addObservers()

        setupRecyclerView()
    }

    private fun addObservers() {
        viewModel.listNotes.observe(this) { result ->
            when (result) {
                is Result.Loading -> {
                    refreshLayout.isRefreshing = true
                    recyclerView.visibility = View.GONE
                }
                is Result.ResultError -> {
                    refreshLayout.isRefreshing = false
                    result.throwable.message?.let { toast(it) }
                }
                is Result.ResultSuccess -> {
                    handleData(result.successData)
                    refreshLayout.isRefreshing = false
                    recyclerView.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun handleData(data: List<Note>) {
        adapter.submitList(data)
    }

    private fun setupRecyclerView() {
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun addListeners() {
        fabBtn.setOnClickListener {
            toast("Go to Activity List Note")
        }
        refreshLayout.setOnRefreshListener {
            viewModel.getNotes()
        }
    }
}

private fun Context.toast(string: String) = Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
