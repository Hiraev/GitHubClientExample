package ru.khiraevmalik.githubclientexample.presentation.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.khiraevmalik.githubclientexample.base.CoroutinesViewModel
import ru.khiraevmalik.githubclientexample.domain_model.ContentResult
import ru.khiraevmalik.githubclientexample.domain_model.GitHubCommitInfo
import ru.khiraevmalik.githubclientexample.interactors.GitHubCommitsInteractor
import ru.khiraevmalik.githubclientexample.routing.Navigator

class RepoViewModel(
        private val navigator: Navigator,
        private val commitsInteractor: GitHubCommitsInteractor
) : CoroutinesViewModel() {

    private val mCommit = MutableLiveData<ContentResult<GitHubCommitInfo>>()

    val commit: LiveData<ContentResult<GitHubCommitInfo>> = mCommit

    fun back() {
        navigator.back()
    }

    fun fetchLastCommit(fullName: String) {
        launch {
            mCommit.postValue(commitsInteractor.fetchLastCommitForRepo(fullName))
        }
    }

}
