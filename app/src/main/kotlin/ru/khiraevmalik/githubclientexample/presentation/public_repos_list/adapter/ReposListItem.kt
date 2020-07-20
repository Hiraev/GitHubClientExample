package ru.khiraevmalik.githubclientexample.presentation.public_repos_list.adapter

import ru.khiraevmalik.githubclientexample.domain_model.GitHubRepository

sealed class ReposListItem {

    class Repo(val info: GitHubRepository) : ReposListItem()

    object LoadingMore : ReposListItem()

    object LoadingMoreError : ReposListItem()

    object FullRepos : ReposListItem()

}
