package ru.khiraevmalik.githubclientexample.presentation.repo

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.fragment_repo.fragment_repo_status_bar
import kotlinx.android.synthetic.main.fragment_repo.fragment_repo_toolbar
import ru.khiraevmalik.githubclientexample.R
import ru.khiraevmalik.githubclientexample.base.BaseFragment
import ru.khiraevmalik.githubclientexample.base.ViewModelFactory
import ru.khiraevmalik.githubclientexample.domain_model.GitHubRepository
import ru.khiraevmalik.githubclientexample.utils.RippleActionInvoker
import ru.khiraevmalik.githubclientexample.utils.addSystemTopPadding
import ru.khiraevmalik.githubclientexample.utils.args
import ru.khiraevmalik.githubclientexample.utils.withParcelable

class RepoFragment : BaseFragment(R.layout.fragment_repo) {

    companion object {
        private const val KEY = "RepoFragment"
        fun newInstance(gitHubRepository: GitHubRepository) = RepoFragment()
                .withParcelable(
                        KEY,
                        RepoState(
                                logo = gitHubRepository.owner.avatar_url,
                                author = gitHubRepository.owner.login,
                                fullName = gitHubRepository.fullName
                        )
                )
    }

    private val state by args<RepoState>(KEY)

    private val vm: RepoViewModel by lazy {
        ViewModelProvider(this, ViewModelFactory).get(RepoViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initViews()
    }

    private fun initObservers() {

    }

    private fun initViews() {
        fragment_repo_toolbar.title = state.fullName
        fragment_repo_status_bar.addSystemTopPadding()
        fragment_repo_toolbar.setNavigationOnClickListener {
            RippleActionInvoker.rippleAction {
                vm.back()
            }
        }
    }

    @Parcelize
    private data class RepoState(
            val logo: String,
            val author: String,
            val fullName: String
    ) : Parcelable

}
