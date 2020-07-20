package ru.khiraevmalik.githubclientexample.presentation.public_repos_list.mvi

import ru.khiraevmalik.githubclientexample.domain_model.GitHubRepository
import ru.khiraevmalik.githubclientexample.presentation.base.PagingStatus

sealed class State {

    object Loading : State()

    object EmptyData : State()

    class Success(val repos: List<GitHubRepository>, val pagingStatus: PagingStatus) : State()

    class Error(val throwable: Throwable?) : State()

}
