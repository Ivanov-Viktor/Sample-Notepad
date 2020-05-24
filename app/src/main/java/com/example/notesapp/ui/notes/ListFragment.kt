package com.example.notesapp.ui.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.notesapp.R
import com.example.notesapp.data.NoteRepository
import com.example.notesapp.ui.notes.NotesAdapter
import kotlinx.android.synthetic.main.fragment_notes_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListFragment : Fragment(), CoroutineScope {
    override val coroutineContext: CoroutineContext = Dispatchers.Main

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notes_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val repo = NoteRepository()
        val adapter = NotesAdapter {
            launch {
                repo.deleteNote(it)
            }
        }
        notesList.adapter = adapter

        launch {
            adapter.addNotes(repo.getAllNotes())
        }

    }

}