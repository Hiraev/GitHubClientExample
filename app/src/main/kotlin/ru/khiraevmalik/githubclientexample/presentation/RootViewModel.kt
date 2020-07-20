package ru.khiraevmalik.githubclientexample.presentation

import androidx.lifecycle.ViewModel
import ru.khiraevmalik.githubclientexample.routing.ContainerHolder
import ru.khiraevmalik.githubclientexample.routing.Navigator

class RootViewModel(
        private val navigator: Navigator
) : ViewModel() {

    fun attachContainerHolder(containerHolder: ContainerHolder) {
        navigator.attach(containerHolder)
    }

    fun detachContainerHolder() {
        navigator.detach()
    }

    fun openReposListFragment() {
        navigator.openReposListFragmentAsRoot()
    }

}
