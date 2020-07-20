package ru.khiraevmalik.githubclientexample.routing

import ru.khiraevmalik.githubclientexample.presentation.public_repos_list.PublicReposListFragment

class Navigator {

    private var containerHolder: ContainerHolder? = null

    fun attach(containerHolder: ContainerHolder) {
        this.containerHolder = containerHolder
    }

    fun detach() {
        containerHolder = null
    }

    fun openReposListFragmentAsRoot() {
        containerHolder?.let { containerHolder ->
            containerHolder
                    .fragmentManager
                    .popBackStack()
            containerHolder
                    .fragmentManager
                    .beginTransaction()
                    .replace(containerHolder.containerId, PublicReposListFragment.newInstance())
                    .commit()
        }
    }

}
