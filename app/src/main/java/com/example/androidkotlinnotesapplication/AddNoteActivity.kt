package com.example.androidkotlinnotesapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidkotlinnotesapplication.databinding.ActivityAddNoteBinding

class AddNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding
    private lateinit var dbHelper : NotesDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        dbHelper = NotesDatabaseHelper(this)

        setContentView(binding.root)

        binding.saveButton.setOnClickListener {
            val title = binding.noteTitleEditText.text.toString()
            val content = binding.noteContentEditText.text.toString()
            val note = Note(0, title, content)
            dbHelper.addNote(note)
            finish()
            Toast.makeText(this, "Note Saved Successfully", Toast.LENGTH_SHORT).show()
        }
    }
}