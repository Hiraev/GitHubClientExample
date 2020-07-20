package ru.khiraevmalik.githubclientexample.presentation.public_repos_list.mvi

import ru.khiraevmalik.githubclientexample.presentation.base.PagingStatus
import ru.khiraevmalik.githubclientexample.presentation.public_repos_list.adapter.ReposListItem

sealed class State {

    object Loading : State()

    object EmptyData : State()

    class Success(val repos: List<ReposListItem.Repo>, val pagingStatus: PagingStatus) : State()

    class Error(val throwable: Throwable?) : State()

}
