package com.example.androidkotlinnotesapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidkotlinnotesapplication.Data.NotesDatabaseHelper
import com.example.androidkotlinnotesapplication.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    private lateinit var db : NotesDatabaseHelper
    private var noteId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = NotesDatabaseHelper(this)

        noteId = intent.getIntExtra("note_id", -1)
        if (noteId == -1) {
            finish()
            return
        }

        val note = db.getNoteById(noteId)
        binding.updateTitleEditText.setText(note?.title)
        binding.updateContentEditText.setText(note?.content)

        binding.updateNoteButton.setOnClickListener {
            val updatedTitle = binding.updateTitleEditText.text.toString()
            val updatedContent = binding.updateContentEditText.text.toString()
            if (note != null) {
                val updatedNote = note.copy(title = updatedTitle, content = updatedContent)
                db.updateNote(updatedNote)
            }
            finish()
            Toast.makeText(this, "Note Updated Successfully", Toast.LENGTH_SHORT).show()
        }


    }
}