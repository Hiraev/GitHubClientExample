package ru.khiraevmalik.githubclientexample.interactors

import ru.khiraevmalik.githubclientexample.domain_model.ContentResult
import ru.khiraevmalik.githubclientexample.domain_model.GitHubCommitInfo

interface GitHubCommitsInteractor {

    suspend fun fetchLastCommitForRepo(repoFullName: String): ContentResult<GitHubCommitInfo>

}
