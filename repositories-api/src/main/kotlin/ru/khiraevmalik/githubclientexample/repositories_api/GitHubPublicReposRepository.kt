package ru.khiraevmalik.githubclientexample.repositories_api

import ru.khiraevmalik.githubclientexample.domain_model.ContentResult
import ru.khiraevmalik.githubclientexample.domain_model.GitHubRepository

interface GitHubPublicReposRepository {

    suspend fun fetchPublicRepositoriesSince(since: Int? = null): ContentResult<List<GitHubRepository>>

}
