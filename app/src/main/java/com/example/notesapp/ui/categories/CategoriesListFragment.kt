package com.example.notesapp.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.notesapp.R
import com.example.notesapp.data.NoteRepository
import com.example.notesapp.ui.createcategory.CategoryCreatingFragment
import com.example.notesapp.ui.createnote.CreatingFragment
import kotlinx.android.synthetic.main.fragment_categories_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoriesListFragment : Fragment(), CoroutineScope {
    override val coroutineContext = Dispatchers.Main

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_categories_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val repo = NoteRepository()
        val adapter = CategoriesAdapter(
            deleteCategory = {
                launch {
                    repo.deleteCategory(it)
                }
            },
            navigateToNoteFragment = { category ->
                fragmentManager?.beginTransaction()
                    ?.replace(
                        R.id.fragmentContainer,
                        CreatingFragment().also {
                            it.arguments = Bundle().also {
                                it.putParcelable(CreatingFragment.CATEGORY_KEY, category)
                            }
                        }
                    )
                    ?.addToBackStack(null)
                    ?.commit()
            }
        )

        categoriesList.adapter = adapter
        launch {
            adapter.addCategories(repo.getAllCategories())
        }

        createNewCategory.setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.replace(R.id.fragmentContainer, CategoryCreatingFragment())
                ?.addToBackStack(null)
                ?.commit()
        }
    }

}