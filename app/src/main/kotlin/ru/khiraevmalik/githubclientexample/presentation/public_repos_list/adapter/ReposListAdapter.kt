package ru.khiraevmalik.githubclientexample.presentation.public_repos_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import ru.khiraevmalik.githubclientexample.R
import ru.khiraevmalik.githubclientexample.presentation.public_repos_list.adapter.view_holders.ReposFullViewHolder
import ru.khiraevmalik.githubclientexample.presentation.public_repos_list.adapter.view_holders.ReposLoadingErrorViewHolder
import ru.khiraevmalik.githubclientexample.presentation.public_repos_list.adapter.view_holders.ReposLoadingViewHolder
import ru.khiraevmalik.githubclientexample.presentation.public_repos_list.adapter.view_holders.ReposViewHolder
import ru.khiraevmalik.githubclientexample.presentation.utils.rippleClick

class ReposListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private enum class ReposItemViewType {
        DATA, LOADING_MORE, LOADING_ERROR, FULL_DATA_ITEM
    }

    private val differ = AsyncListDiffer(this, ReposListCallback)
    private var itemClickListener: (ReposListItem) -> Unit = { _ -> }
    private var onRetryLoadMoreClickListener: () -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = when (viewType) {
        ReposItemViewType.DATA.ordinal -> inflateReposItem(parent)
        ReposItemViewType.LOADING_ERROR.ordinal -> inflateReposLoadingError(parent)
        ReposItemViewType.LOADING_MORE.ordinal -> inflateLoadingMoreItem(parent)
        ReposItemViewType.FULL_DATA_ITEM.ordinal -> inflateFullDataItem(parent)

        else -> throw IllegalArgumentException("Unknown view type: $viewType")
    }

    override fun getItemViewType(position: Int): Int = when (differ.currentList[position]) {
        is ReposListItem.Repo -> ReposItemViewType.DATA.ordinal
        is ReposListItem.FullRepos -> ReposItemViewType.FULL_DATA_ITEM.ordinal
        is ReposListItem.LoadingMore -> ReposItemViewType.LOADING_MORE.ordinal
        is ReposListItem.LoadingMoreError -> ReposItemViewType.LOADING_ERROR.ordinal
    }

    override fun getItemCount() = differ.currentList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ReposViewHolder -> {
                holder.bind((differ.currentList[position] as ReposListItem.Repo).info)
                holder.itemView.rippleClick {
                    itemClickListener.invoke(differ.currentList[position])
                }
            }
        }
    }

    fun submitList(items: List<ReposListItem>) {
        differ.submitList(items)
    }

    fun setItemClickListener(listener: (ReposListItem) -> Unit) {
        this.itemClickListener = listener
    }

    fun setOnRetryMoreClickListener(listener: () -> Unit) {
        this.onRetryLoadMoreClickListener = listener
    }

    private fun inflateReposItem(parent: ViewGroup) =
            ReposViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_github_repo, parent, false))

    private fun inflateReposLoadingError(parent: ViewGroup) =
            ReposLoadingErrorViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.item_github_repos_error_loading_more, parent, false),
                    onRetryLoadMoreClickListener
            )

    private fun inflateLoadingMoreItem(parent: ViewGroup) =
            ReposLoadingViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_github_repo_loading, parent, false))

    private fun inflateFullDataItem(parent: ViewGroup) =
            ReposFullViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_all_github_repos_loaded, parent, false))

}
