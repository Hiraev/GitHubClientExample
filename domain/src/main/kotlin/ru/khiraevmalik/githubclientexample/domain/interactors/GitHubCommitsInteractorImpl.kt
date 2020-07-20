package ru.khiraevmalik.githubclientexample.domain.interactors

import ru.khiraevmalik.githubclientexample.domain_model.ContentResult
import ru.khiraevmalik.githubclientexample.domain_model.GitHubCommitInfo
import ru.khiraevmalik.githubclientexample.interactors.GitHubCommitsInteractor
import ru.khiraevmalik.githubclientexample.repositories_api.GitHubCommitsRepository

class GitHubCommitsInteractorImpl(
        private val gitHubCommitsRepository: GitHubCommitsRepository
) : BaseInteractor(), GitHubCommitsInteractor {

    override suspend fun fetchLastCommitForRepo(repoFullName: String): ContentResult<GitHubCommitInfo> =
            withDefault {
                when (val res = gitHubCommitsRepository.fetchCommitsForRepo(repoFullName)) {
                    is ContentResult.Success -> ContentResult.Success(res.data.first())
                    is ContentResult.Error -> ContentResult.Error<GitHubCommitInfo>(res.throwable)
                }
            }

}
