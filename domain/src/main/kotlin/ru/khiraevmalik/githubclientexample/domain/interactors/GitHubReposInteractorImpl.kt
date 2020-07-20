package ru.khiraevmalik.githubclientexample.domain.interactors

import ru.khiraevmalik.githubclientexample.domain_model.ContentResult
import ru.khiraevmalik.githubclientexample.domain_model.GitHubRepository
import ru.khiraevmalik.githubclientexample.interactors.GitHubReposInteractor
import ru.khiraevmalik.githubclientexample.repositories_api.GitHubPublicReposRepository

class GitHubReposInteractorImpl(
        private val gitHubPublicReposRepository: GitHubPublicReposRepository
) : BaseInteractor(), GitHubReposInteractor {

    override suspend fun fetchPublicRepos(since: Int?): ContentResult<List<GitHubRepository>> = withDefault {
        gitHubPublicReposRepository.fetchPublicRepositories(since)
    }

}
