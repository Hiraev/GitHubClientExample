package ru.khiraevmalik.githubclientexample.presentation.public_repos_list.adapter

import androidx.recyclerview.widget.DiffUtil

object ReposListCallback : DiffUtil.ItemCallback<ReposListItem>() {

    override fun areItemsTheSame(oldItem: ReposListItem, newItem: ReposListItem): Boolean = when {
        oldItem is ReposListItem.FullRepos && newItem is ReposListItem.FullRepos ||
                oldItem is ReposListItem.LoadingMore && newItem is ReposListItem.LoadingMore ||
                oldItem is ReposListItem.LoadingMoreError && newItem is ReposListItem.LoadingMoreError -> true
        oldItem is ReposListItem.Repo && newItem is ReposListItem.Repo -> oldItem.info.id == newItem.info.id
        else -> false
    }

    override fun areContentsTheSame(oldItem: ReposListItem, newItem: ReposListItem): Boolean = when {
        oldItem is ReposListItem.FullRepos && newItem is ReposListItem.FullRepos ||
                oldItem is ReposListItem.LoadingMore && newItem is ReposListItem.LoadingMore ||
                oldItem is ReposListItem.LoadingMoreError && newItem is ReposListItem.LoadingMoreError -> true
        oldItem is ReposListItem.Repo && newItem is ReposListItem.Repo -> {
            oldItem.info.fullName == newItem.info.fullName &&
                    oldItem.info.owner.login == newItem.info.owner.login &&
                    oldItem.info.owner.avatar_url == newItem.info.owner.avatar_url
        }
        else -> false
    }

}
