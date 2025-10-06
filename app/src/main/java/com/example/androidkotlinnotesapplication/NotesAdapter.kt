package com.example.androidkotlinnotesapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter(context: Context, private var notesList: List<Note>): RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    class NoteViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        // Initialize your views here, e.g.:
        val titleTextView : TextView = itemView.findViewById(R.id.noteTitleTextView)
        val contentTextView : TextView = itemView.findViewById(R.id.noteContentTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notesList[position]
        holder.titleTextView.text = note.title
        holder.contentTextView.text = note.content
    }

    override fun getItemCount(): Int = notesList.size

    fun refreshData(newNotesList: List<Note>) {
        notesList = newNotesList
        notifyDataSetChanged()
    }

}