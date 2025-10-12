package com.example.androidkotlinnotesapplication.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidkotlinnotesapplication.Data.Note
import com.example.androidkotlinnotesapplication.Data.NotesDatabaseHelper
import com.example.androidkotlinnotesapplication.R
import com.example.androidkotlinnotesapplication.UpdateActivity

class NotesAdapter(context: Context, private var notesList: List<Note>): RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    private val db : NotesDatabaseHelper = NotesDatabaseHelper(context)

    class NoteViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        // Initialize your views here, e.g.:
        val titleTextView : TextView = itemView.findViewById(R.id.noteTitleTextView)
        val contentTextView : TextView = itemView.findViewById(R.id.noteContentTextView)
        val updateButton : ImageView = itemView.findViewById(R.id.noteUpdateButton)
        val deleteButton : ImageView = itemView.findViewById(R.id.noteDeleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notesList[position]
        holder.titleTextView.text = note.title
        holder.contentTextView.text = note.content

        holder.updateButton.setOnClickListener {
            val intent = Intent(holder.itemView.context, UpdateActivity::class.java).apply {
                putExtra("note_id", note.id)
            }
            holder.itemView.context.startActivity(intent)
        }

        holder.deleteButton.setOnClickListener {
            db.deleteNote(note.id)
            refreshData(db.getAllNotes())
        }

        holder
    }

    override fun getItemCount(): Int = notesList.size

    fun refreshData(newNotesList: List<Note>) {
        notesList = newNotesList
        notifyDataSetChanged()
    }

}