package com.salman.roomtask.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.salman.roomtask.R
import com.salman.roomtask.databinding.FragmentHomeBinding
import com.salman.roomtask.model.Category
import com.salman.roomtask.model.Note
import com.salman.roomtask.ui.home.adapter.CategoriesAdapter
import com.salman.roomtask.ui.home.adapter.NoteAdapter
import com.salman.roomtask.ui.util.submitList

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 7/18/2023.
 */
class HomeFragment : Fragment() {
    private val viewModel by viewModels<HomeViewModel>()

    private var _binding: FragmentHomeBinding? = null
    private val binding
        get() = requireNotNull(_binding)

    private val adapter by lazy {
        NoteAdapter()
    }

    private lateinit var dialog: AlertDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerviewNotes.adapter = adapter
        viewModel.categories.observe(viewLifecycleOwner) { categories ->
            binding.chipGroupCategories.submitList(categories) { category ->
                // TODO navigate to details fragment with category id
                findNavController().navigate(R.id.detailsFragment, Bundle().apply {
                    category.id?.let { putInt("categoryId", it) }
                    putString("categoryName", category.title)
                })
            }
        }

        viewModel.notes.observe(viewLifecycleOwner) { notes ->
            println(notes)
            adapter.submitList(notes)
            adapter.setItemClickListener(object : NoteAdapter.OnItemClickListener{
                override fun onNoteClickListener(note: Note) {
                    buildSelectCategoryDialog(note)
                    dialog.show()
                }

            })
        }
    }

    private fun buildSelectCategoryDialog(note: Note) {
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.select_category_dialog, null)
        val builder = AlertDialog.Builder(activity)

        builder.setCancelable(true)
        builder.setTitle("SELECT CATEGORY")
        val recycler = dialogView.findViewById<RecyclerView>(R.id.recycler_select_category)
        val layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, true)
        layoutManager.stackFromEnd = true
        layoutManager.reverseLayout = true
        recycler.layoutManager = layoutManager

        viewModel.categories.observe(viewLifecycleOwner){list ->
            val categoriesAdapter = CategoriesAdapter(list)
            categoriesAdapter.setItemClickListener(object : CategoriesAdapter.OnItemClickListener{
                override fun onItemClicked(category: Category) {
                    note.categoryId = category.id
                    viewModel.updateNote(note)
                }

            })
            recycler.adapter = categoriesAdapter
        }
        builder.setView(dialogView)
        dialog = builder.create()
    }

}