package ru.khiraevmalik.githubclientexample.routing

import androidx.fragment.app.FragmentTransaction
import ru.khiraevmalik.githubclientexample.R
import ru.khiraevmalik.githubclientexample.domain_model.GitHubRepository
import ru.khiraevmalik.githubclientexample.presentation.public_repos_list.PublicReposListFragment
import ru.khiraevmalik.githubclientexample.presentation.repo.RepoFragment

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

    fun openReposFragment(gitHubRepository: GitHubRepository) {
        containerHolder?.let { containerHolder ->
            containerHolder
                    .fragmentManager
                    .beginTransaction()
                    .slide()
                    .addToBackStack(null)
                    .replace(containerHolder.containerId, RepoFragment.newInstance(gitHubRepository))
                    .commit()
        }
    }

    fun back() {
        containerHolder?.fragmentManager?.popBackStack()
    }

    private fun FragmentTransaction.slide() = setCustomAnimations(
            R.anim.enter_from_right,
            R.anim.exit_to_left,
            R.anim.enter_from_left,
            R.anim.exit_to_right
    )

}
