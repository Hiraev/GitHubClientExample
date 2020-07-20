package ru.khiraevmalik.githubclientexample.presentation.public_repos_list.mvi

import kotlinx.coroutines.Job
import ru.khiraevmalik.githubclientexample.domain_model.ContentResult
import ru.khiraevmalik.githubclientexample.interactors.GitHubReposInteractor
import ru.khiraevmalik.githubclientexample.presentation.base.DisposableMiddleware
import ru.khiraevmalik.githubclientexample.presentation.base.PagingStatus
import ru.khiraevmalik.githubclientexample.presentation.public_repos_list.adapter.ReposListItem
import java.util.concurrent.atomic.AtomicBoolean

class LoadPublicReposMiddleware(
        private val gitHubReposInteractor: GitHubReposInteractor
) : DisposableMiddleware<Action, Action.Effect, State, Unit>() {

    private var lastId: Int? = null
    private var loadingMoreJob: Job? = null
    private var hasMoreRepositories = AtomicBoolean(true)

    override fun handle(action: Action, state: State) {
        when (action) {
            is Action.User.LoadRepos -> {
                loadRepos()
            }
            is Action.User.LoadMore -> {
                (state as? State.Success)?.let { loadMore(state.repos) }
            }
            is Action.User.Retry -> {
                loadRepos()
            }
        }
    }

    private fun loadRepos() {
        launch {
            effectOnMain(Action.Effect.Loading)
            when (val result = gitHubReposInteractor.fetchPublicRepos()) {
                is ContentResult.Success -> {
                    if (result.data.isEmpty()) {
                        effectOnMain(Action.Effect.Empty)
                    } else {
                        effectOnMain(Action.Effect.Success(result.data.map(ReposListItem::Repo), PagingStatus.HAS_MORE))
                        lastId = result.data.last().id
                    }
                }
                is ContentResult.Error -> {
                    effectOnMain(Action.Effect.Error(result.throwable))
                }
            }
        }
    }

    private fun loadMore(old: List<ReposListItem.Repo>) {
        if (hasMoreRepositories.get() || loadingMoreJob?.isActive == true) return
        loadingMoreJob = launch {
            effectOnMain(Action.Effect.Success(old, PagingStatus.LOADING))
            when (val res = gitHubReposInteractor.fetchPublicRepos(lastId)) {
                is ContentResult.Error -> {
                    effectOnMain(Action.Effect.Success(old, PagingStatus.ERROR))
                }
                is ContentResult.Success -> {
                    if (res.data.isEmpty()) {
                        effectOnMain(Action.Effect.Success(old, PagingStatus.FULL))
                        hasMoreRepositories.set(false)
                    } else {
                        effectOnMain(Action.Effect.Success(old + res.data.map(ReposListItem::Repo), PagingStatus.HAS_MORE))
                    }
                }
            }
        }
    }

}
