package com.projects.notes.ui.notelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.projects.notes.databinding.NoteListItemBinding
import com.projects.notes.model.Note

class NoteListAdapter(
    private val noteList: List<Note>
) : ListAdapter<Note, NoteListAdapter.NoteListViewHolder>(NoteDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteListViewHolder {
        val binding: NoteListItemBinding =
            NoteListItemBinding.inflate(LayoutInflater.from(parent.context))
        return NoteListViewHolder(binding)
    }


    override fun getItemCount(): Int = noteList.size

    override fun onBindViewHolder(holder: NoteListViewHolder, position: Int) {
        holder.bind(noteList[position])
    }

    class NoteListViewHolder(binding: NoteListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val noteTitle = binding.itemNoteTitle
        private val noteDescription = binding.itemNoteBody

        fun bind(note: Note) {
            noteTitle.text = note.title
            noteDescription.text = note.body
        }
    }
}

private class NoteDiffUtil : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.idNota == newItem.idNota
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem == newItem
    }
}