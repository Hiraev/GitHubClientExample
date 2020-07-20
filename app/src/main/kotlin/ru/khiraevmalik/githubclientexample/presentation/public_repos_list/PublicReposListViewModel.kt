package ru.khiraevmalik.githubclientexample.presentation.public_repos_list

import ru.khiraevmalik.githubclientexample.base.CoroutinesMviViewModel
import ru.khiraevmalik.githubclientexample.presentation.public_repos_list.mvi.Action
import ru.khiraevmalik.githubclientexample.presentation.public_repos_list.mvi.PublicReposListStore
import ru.khiraevmalik.githubclientexample.presentation.public_repos_list.mvi.State

class PublicReposListViewModel(
        store: PublicReposListStore
) : CoroutinesMviViewModel<Action, Action.User, Action.Effect, State, Unit>(store) {

    init {
        proceed(Action.User.LoadRepos)
    }

}
