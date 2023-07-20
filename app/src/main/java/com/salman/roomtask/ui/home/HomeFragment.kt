package com.salman.roomtask.ui.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.salman.roomtask.R
import com.salman.roomtask.databinding.FragmentHomeBinding
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
                val action = HomeFragmentDirections.startDetailsFragment(category.id!!, category.title)
                findNavController().navigate(action)
            }
        }

        viewModel.notes.observe(viewLifecycleOwner) { notes ->
            println(notes)
            adapter.submitList(notes)
        }
    }

}