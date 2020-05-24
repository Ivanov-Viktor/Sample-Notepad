package com.example.notesapp.ui.createcategory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.notesapp.R
import com.example.notesapp.data.NoteRepository
import kotlinx.android.synthetic.main.fragment_category_creating.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryCreatingFragment : Fragment(), CoroutineScope {
    override val coroutineContext = Dispatchers.Main

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_category_creating, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val repo = NoteRepository()

        createNew.setOnClickListener {
            val name = categoryName.text.toString()

            if (name.isNotBlank()) {
                launch {
                    val id = repo.createNewCategory(name)
                    Toast.makeText(context, "Inserted with id $id", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}