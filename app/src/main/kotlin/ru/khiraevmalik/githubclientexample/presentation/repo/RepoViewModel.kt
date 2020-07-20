package ru.khiraevmalik.githubclientexample.presentation.repo

import ru.khiraevmalik.githubclientexample.base.CoroutinesViewModel
import ru.khiraevmalik.githubclientexample.routing.Navigator

class RepoViewModel(private val navigator: Navigator) : CoroutinesViewModel() {

    fun back() {
        navigator.back()
    }

}
