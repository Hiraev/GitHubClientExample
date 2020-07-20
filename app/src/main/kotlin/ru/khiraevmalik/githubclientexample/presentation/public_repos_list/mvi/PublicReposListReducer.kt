package ru.khiraevmalik.githubclientexample.presentation.public_repos_list.mvi

import ru.khiraevmalik.githubclientexample.mvi_no_reactive.Reducer

class PublicReposListReducer : Reducer<State, Action> {

    override fun reduce(action: Action, state: State): State = when (action) {
        is Action.User.LoadMore,
        is Action.User.LoadRepos,
        is Action.User.Retry -> state
        is Action.Effect.Loading -> State.Loading
        is Action.Effect.Error -> State.Error(action.throwable)
        is Action.Effect.Success -> State.Success(action.news, action.pagingStatus)
        is Action.Effect.Empty -> State.EmptyData
    }

}
