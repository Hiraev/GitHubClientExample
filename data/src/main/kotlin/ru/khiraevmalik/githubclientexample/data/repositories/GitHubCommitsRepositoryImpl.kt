package ru.khiraevmalik.githubclientexample.data.repositories

import ru.khiraevmalik.githubclientexample.data.api.GitHubApi
import ru.khiraevmalik.githubclientexample.data.converters.GitHubCommitsConverter
import ru.khiraevmalik.githubclientexample.domain_model.ContentResult
import ru.khiraevmalik.githubclientexample.domain_model.GitHubCommitInfo
import ru.khiraevmalik.githubclientexample.repositories_api.GitHubCommitsRepository

class GitHubCommitsRepositoryImpl(
        private val gitHubApi: GitHubApi
) : GitHubCommitsRepository {

    override suspend fun fetchCommitsForRepo(repoFullName: String): ContentResult<List<GitHubCommitInfo>> = try {
        val commits = gitHubApi.fetchCommits(repoFullName).map(GitHubCommitsConverter::convert)
        ContentResult.Success(commits)
    } catch (t: Throwable) {
        ContentResult.Error(t)
    }

}
