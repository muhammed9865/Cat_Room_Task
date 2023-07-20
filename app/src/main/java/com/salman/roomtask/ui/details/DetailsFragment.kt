package com.salman.roomtask.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.salman.roomtask.databinding.FragmentDetailsBinding
import com.salman.roomtask.ui.home.adapter.NoteAdapter


class DetailsFragment : Fragment() {
    private val viewModel by viewModels<DetailsViewModel>()

    private var _binding: FragmentDetailsBinding? = null

    private val binding
        get() = requireNotNull(_binding)

    private val adapter by lazy {
        NoteAdapter()
    }

    val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater)

        val id = args.categoryId
        val title = args.categoryName

        binding.recyclerviewNotes.adapter = adapter
        binding.categoryName.text = title

        viewModel.loadCategory(id)
        viewModel.notes.observe(viewLifecycleOwner){
            it?.let { list ->
                adapter.submitList(list)
            }
        }

        return binding.root
    }
}