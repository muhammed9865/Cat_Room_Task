package com.salman.roomtask.ui.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.salman.roomtask.databinding.ListItemNoteBinding
import com.salman.roomtask.model.Note

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 7/18/2023.
 */
class NoteViewHolder(
    private val binding: ListItemNoteBinding,
    private val listener: NoteAdapter.OnItemClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(note: Note) {
        binding.textViewText.text = note.text
        binding.textViewText.setOnClickListener {
            listener.onNoteClickListener(note)
        }
    }

}