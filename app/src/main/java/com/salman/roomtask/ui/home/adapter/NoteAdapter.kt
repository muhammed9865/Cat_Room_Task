package com.salman.roomtask.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.ListAdapter
import com.salman.roomtask.databinding.ListItemNoteBinding
import com.salman.roomtask.model.Note

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 7/18/2023.
 */
class NoteAdapter : ListAdapter<Note, NoteViewHolder>(NoteCallback) {

    private lateinit var mListener: OnItemClickListener

    interface OnItemClickListener {
        fun onNoteClickListener(note: Note)
    }

    fun setItemClickListener(listener: OnItemClickListener){
        mListener = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ListItemNoteBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NoteViewHolder(binding, mListener)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}