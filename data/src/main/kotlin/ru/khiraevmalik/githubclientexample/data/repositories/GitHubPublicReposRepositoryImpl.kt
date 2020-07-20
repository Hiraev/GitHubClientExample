package ru.khiraevmalik.githubclientexample.data.repositories

import ru.khiraevmalik.githubclientexample.data.api.GitHubApi
import ru.khiraevmalik.githubclientexample.data.converters.GitHubRepositoryConverter
import ru.khiraevmalik.githubclientexample.domain_model.ContentResult
import ru.khiraevmalik.githubclientexample.domain_model.GitHubRepository
import ru.khiraevmalik.githubclientexample.repositories_api.GitHubPublicReposRepository

class GitHubPublicReposRepositoryImpl(
        private val gitHubApi: GitHubApi
) : GitHubPublicReposRepository {

    override suspend fun fetchPublicRepositoriesSince(since: Int?): ContentResult<List<GitHubRepository>> = try {
        val converted = gitHubApi.fetchPublicRepositories(since).map(GitHubRepositoryConverter::convert)
        ContentResult.Success(converted)
    } catch (t: Throwable) {
        ContentResult.Error(t)
    }

}
