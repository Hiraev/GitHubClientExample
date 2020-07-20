package ru.khiraevmalik.githubclientexample.interactors

import ru.khiraevmalik.githubclientexample.domain_model.ContentResult
import ru.khiraevmalik.githubclientexample.domain_model.GitHubRepository

interface GitHubReposInteractor {

    suspend fun fetchPublicRepos(since: Int? = null): ContentResult<List<GitHubRepository>>

}
