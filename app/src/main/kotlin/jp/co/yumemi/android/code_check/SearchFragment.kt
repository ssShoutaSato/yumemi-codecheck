/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.code_check

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.*
import jp.co.yumemi.android.code_check.databinding.FragmentSearchBinding

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
        val context = requireContext()
        val viewModel = SearchViewModel()
        val layoutManager = LinearLayoutManager(context)
        val dividerItemDecoration = DividerItemDecoration(context, layoutManager.orientation)
        val adapter = CustomAdapter(object : CustomAdapter.OnItemClickListener {
            override fun onItemClicked(item: RepositoryInformation) {
                navigateToRepositoryInformationFragment(item)
            }
        })

        binding.searchInputText.setOnEditorActionListener { editText, action, _ ->
            if (action == EditorInfo.IME_ACTION_SEARCH) {
                editText.text.toString().let {
                    viewModel.searchResults(it).apply {
                        adapter.submitList(this)
                    }
                }
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }

        binding.recyclerView.also {
            it.layoutManager = layoutManager
            it.addItemDecoration(dividerItemDecoration)
            it.adapter = adapter
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

val diffUtil = object : DiffUtil.ItemCallback<RepositoryInformation>() {
    override fun areItemsTheSame(oldItem: RepositoryInformation, newItem: RepositoryInformation): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: RepositoryInformation, newItem: RepositoryInformation): Boolean {
        return oldItem == newItem
    }
}

/**
 * This adapter for displaying a list of search results.
 *
 * Notify that an item has been clicked with [OnItemClickListener.itemClick].
 *
 * @param itemClickListener The listener for notifying that an item has been clicked.
 */
class CustomAdapter(private val itemClickListener: OnItemClickListener) :
    ListAdapter<RepositoryInformation, CustomAdapter.ViewHolder>(diffUtil) {
    /**
     * [ListAdapter.onCreateViewHolder]
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item, parent, false)
        return ViewHolder(view)
    }

    /**
     * [ListAdapter.onBindViewHolder]
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        val view = holder.itemView.findViewById<View>(R.id.repositoryNameView)
        if (view is TextView) {
            view.text = item.name
        }
        holder.itemView.setOnClickListener { itemClickListener.onItemClicked(item) }
    }

    /**
     * This class for holding the view displayed in the list.
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    /**
     * The listener for notifying that an item has been clicked.
     */
    interface OnItemClickListener {
        /**
         * Notify that item is clicked.
         */
        fun onItemClicked(item: RepositoryInformation)
    }
}
