package ru.khiraevmalik.githubclientexample.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.khiraevmalik.githubclientexample.di.DiContainer
import ru.khiraevmalik.githubclientexample.presentation.RootViewModel
import ru.khiraevmalik.githubclientexample.presentation.public_repos_list.PublicReposListViewModel
import ru.khiraevmalik.githubclientexample.presentation.repo.RepoViewModel

object ViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            when (modelClass) {
                RootViewModel::class.java -> RootViewModel(DiContainer.provideNavigator())
                PublicReposListViewModel::class.java -> PublicReposListViewModel(DiContainer.provideNavigator(), DiContainer.providePublicReposListStore())
                RepoViewModel::class.java -> RepoViewModel(DiContainer.provideNavigator())

                else -> throw IllegalArgumentException("Can't find model")
            } as T

}
