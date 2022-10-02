package jp.co.yumemi.android.code_check.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import jp.co.yumemi.android.code_check.R
import jp.co.yumemi.android.code_check.model.data.RepositoryInformation

private val diffUtil = object : DiffUtil.ItemCallback<RepositoryInformation>() {
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
 * Notify that an item has been clicked with [OnItemClickListener.onItemClicked].
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
     *
     * @param view The view displayed in the list.
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    /**
     * The listener for notifying that an item has been clicked.
     */
    interface OnItemClickListener {
        /**
         * Notify that item is clicked.
         *
         * @param item An item is clicked.
         */
        fun onItemClicked(item: RepositoryInformation)
    }
}
