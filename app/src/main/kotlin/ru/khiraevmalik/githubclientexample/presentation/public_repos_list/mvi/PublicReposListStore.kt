package ru.khiraevmalik.githubclientexample.presentation.public_repos_list.mvi

import ru.khiraevmalik.githubclientexample.mvi_no_reactive.Middleware
import ru.khiraevmalik.githubclientexample.mvi_no_reactive.Reducer
import ru.khiraevmalik.githubclientexample.mvi_no_reactive.Store

class PublicReposListStore(
        reducer: Reducer<State, Action>,
        middleware: List<Middleware<Action, Action.Effect, State, Unit>>
) : Store<Action, Action.User, Action.Effect, State, Unit>(reducer, middleware, State.Loading)
