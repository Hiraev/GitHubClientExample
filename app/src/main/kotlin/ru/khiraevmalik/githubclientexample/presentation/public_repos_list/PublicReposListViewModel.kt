package ru.khiraevmalik.githubclientexample.presentation.public_repos_list

import ru.khiraevmalik.githubclientexample.base.CoroutinesMviViewModel
import ru.khiraevmalik.githubclientexample.domain_model.GitHubRepository
import ru.khiraevmalik.githubclientexample.presentation.public_repos_list.mvi.Action
import ru.khiraevmalik.githubclientexample.presentation.public_repos_list.mvi.PublicReposListStore
import ru.khiraevmalik.githubclientexample.presentation.public_repos_list.mvi.State
import ru.khiraevmalik.githubclientexample.routing.Navigator

class PublicReposListViewModel(
        private val navigator: Navigator,
        store: PublicReposListStore
) : CoroutinesMviViewModel<Action, Action.User, Action.Effect, State, Unit>(store) {

    init {
        proceed(Action.User.LoadRepos)
    }

    fun openRepo(gitHubRepository: GitHubRepository) {
        navigator.openReposFragment(gitHubRepository)
    }

}
