package ru.khiraevmalik.githubclientexample.repositories_api

import ru.khiraevmalik.githubclientexample.domain_model.ContentResult
import ru.khiraevmalik.githubclientexample.domain_model.GitHubCommitInfo

interface GitHubCommitsRepository {

    suspend fun fetchCommitsForRepo(repoFullName: String): ContentResult<List<GitHubCommitInfo>>

}
