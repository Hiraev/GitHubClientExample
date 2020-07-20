package ru.khiraevmalik.githubclientexample.presentation.repo

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.fragment_repo.fragment_repo_author_logo
import kotlinx.android.synthetic.main.fragment_repo.fragment_repo_author_name
import kotlinx.android.synthetic.main.fragment_repo.fragment_repo_commit_author_email
import kotlinx.android.synthetic.main.fragment_repo.fragment_repo_commit_author_login
import kotlinx.android.synthetic.main.fragment_repo.fragment_repo_commit_message
import kotlinx.android.synthetic.main.fragment_repo.fragment_repo_commit_parents
import kotlinx.android.synthetic.main.fragment_repo.fragment_repo_error_loading_stub
import kotlinx.android.synthetic.main.fragment_repo.fragment_repo_last_commit_date
import kotlinx.android.synthetic.main.fragment_repo.fragment_repo_last_commit_info
import kotlinx.android.synthetic.main.fragment_repo.fragment_repo_last_commit_sha
import kotlinx.android.synthetic.main.fragment_repo.fragment_repo_progress_bar
import kotlinx.android.synthetic.main.fragment_repo.fragment_repo_status_bar
import kotlinx.android.synthetic.main.fragment_repo.fragment_repo_toolbar
import kotlinx.android.synthetic.main.include_error_loading_commits_with_retry_stub.include_error_loading_commits_with_retry_stub_button
import ru.khiraevmalik.githubclientexample.R
import ru.khiraevmalik.githubclientexample.base.BaseFragment
import ru.khiraevmalik.githubclientexample.base.ViewModelFactory
import ru.khiraevmalik.githubclientexample.domain_model.ContentResult
import ru.khiraevmalik.githubclientexample.domain_model.GitHubRepository
import ru.khiraevmalik.githubclientexample.utils.RippleActionInvoker
import ru.khiraevmalik.githubclientexample.utils.addSystemTopPadding
import ru.khiraevmalik.githubclientexample.utils.args
import ru.khiraevmalik.githubclientexample.utils.rippleClick
import ru.khiraevmalik.githubclientexample.utils.visibleWithCheck
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
        vm.fetchLastCommit(state.fullName)
    }

    private fun initObservers() {
        vm.commit.observe(this, Observer { contentResult ->
            when (contentResult) {
                is ContentResult.Success -> {
                    fragment_repo_last_commit_sha.text = contentResult.data.sha.substring(0, 7)
                    fragment_repo_last_commit_date.text = contentResult.data.date
                    fragment_repo_commit_message.text = contentResult.data.message
                    fragment_repo_commit_author_login.text = contentResult.data.authorName
                    fragment_repo_commit_author_email.text = contentResult.data.authorEmail
                    fragment_repo_commit_parents.text = contentResult.data.parents.map { it.substring(0, 7) }.joinToString()
                }
            }
            fragment_repo_progress_bar.visibleWithCheck(false)
            fragment_repo_last_commit_info.visibleWithCheck(contentResult is ContentResult.Success)
            fragment_repo_error_loading_stub.visibleWithCheck(contentResult is ContentResult.Error)
        })
    }

    private fun initViews() {
        fragment_repo_toolbar.title = state.fullName
        fragment_repo_status_bar.addSystemTopPadding()
        fragment_repo_toolbar.setNavigationOnClickListener {
            RippleActionInvoker.rippleAction {
                vm.back()
            }
        }
        include_error_loading_commits_with_retry_stub_button.rippleClick {
            fragment_repo_progress_bar.visibleWithCheck(true)
            vm.fetchLastCommit(state.fullName)
        }
        Glide.with(fragment_repo_author_logo)
                .load(state.logo)
                .circleCrop()
                .into(fragment_repo_author_logo)
        fragment_repo_author_name.text = state.author
    }

    @Parcelize
    private data class RepoState(
            val logo: String,
            val author: String,
            val fullName: String
    ) : Parcelable

}
