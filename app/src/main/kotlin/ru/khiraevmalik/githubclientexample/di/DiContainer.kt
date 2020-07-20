package ru.khiraevmalik.githubclientexample.di

import ru.khiraevmalik.githubclientexample.di_container.DiDomainContainer
import ru.khiraevmalik.githubclientexample.presentation.public_repos_list.mvi.LoadPublicReposMiddleware
import ru.khiraevmalik.githubclientexample.presentation.public_repos_list.mvi.PublicReposListReducer
import ru.khiraevmalik.githubclientexample.presentation.public_repos_list.mvi.PublicReposListStore
import ru.khiraevmalik.githubclientexample.routing.Navigator

object DiContainer {

    private val navigator by lazy {
        Navigator()
    }

    fun provideNavigator(): Navigator = navigator

    fun provideGitHubReposInteractor() = DiDomainContainer.provideReposInteractor()

    fun providePublicReposListStore(): PublicReposListStore = PublicReposListStore(
            reducer = providePublicReposListReducer(),
            middleware = providePublicReposListMiddleware()
    )

    private fun providePublicReposListReducer() = PublicReposListReducer()

    private fun providePublicReposListMiddleware() = listOf(
            LoadPublicReposMiddleware(DiDomainContainer.provideReposInteractor())
    )

}
