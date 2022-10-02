/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.code_check.ui.search

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.*
import jp.co.yumemi.android.code_check.R
import jp.co.yumemi.android.code_check.databinding.FragmentSearchBinding
import jp.co.yumemi.android.code_check.model.data.RepositoryInformation

/**
 * This fragment for displaying the search result.
 */
class SearchFragment : Fragment(R.layout.fragment_search) {
    /**
     * [Fragment.onViewCreated]
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentSearchBinding.bind(view)
        val viewModel = SearchViewModel()
        val adapter = CustomAdapter(object : CustomAdapter.OnItemClickListener {
            override fun onItemClicked(item: RepositoryInformation) {
                navigateToRepositoryInformationFragment(item)
            }
        })

        binding.searchInputText.setOnEditorActionListener { editText, action, _ ->
            if (action == EditorInfo.IME_ACTION_SEARCH) {
                editText.text.toString().let {
                    viewModel.search(it)
                }
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }

        val context = requireContext()
        val layoutManager = LinearLayoutManager(context)
        binding.recyclerView.also {
            it.layoutManager = layoutManager
            it.addItemDecoration(DividerItemDecoration(context, layoutManager.orientation))
            it.adapter = adapter
        }

        viewModel.list.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    /**
     * Navigate to the repository information fragment.
     *
     * @param repositoryInformation An item to display on repository information fragment.
     */
    fun navigateToRepositoryInformationFragment(repositoryInformation: RepositoryInformation) {
        val action =
            SearchFragmentDirections.actionRepositoriesFragmentToRepositoryFragment(repositoryInformation = repositoryInformation)
        findNavController().navigate(action)
    }
}
