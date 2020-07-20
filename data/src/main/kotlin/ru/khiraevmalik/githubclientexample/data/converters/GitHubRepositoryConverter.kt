package ru.khiraevmalik.githubclientexample.data.converters

import ru.khiraevmalik.githubclientexample.data.model.NWGitHubRepositoryInfo
import ru.khiraevmalik.githubclientexample.domain_model.GitHubRepository

object GitHubRepositoryConverter {

    fun convert(gitHubRepository: NWGitHubRepositoryInfo) =
            GitHubRepository(
                    fullName = gitHubRepository.full_name,
                    owner = GitHubOwnerConverter.convert(gitHubRepository.owner)
            )

}
