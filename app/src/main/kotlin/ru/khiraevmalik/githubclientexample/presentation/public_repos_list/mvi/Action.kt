package ru.khiraevmalik.githubclientexample.presentation.public_repos_list.mvi

import ru.khiraevmalik.githubclientexample.domain_model.GitHubRepository
import ru.khiraevmalik.githubclientexample.presentation.base.PagingStatus

sealed class Action {

    sealed class User : Action() {
        object LoadRepos : User()
        object Retry : User()
        object LoadMore : User()
    }

    sealed class Effect : Action() {
        object Loading : Effect()
        object Empty : Effect()
        class Error(val throwable: Throwable?) : Effect()
        class Success(val news: List<GitHubRepository>, val pagingStatus: PagingStatus) : Effect()
    }

}
