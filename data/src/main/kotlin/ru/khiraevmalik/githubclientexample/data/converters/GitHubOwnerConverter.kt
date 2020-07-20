package ru.khiraevmalik.githubclientexample.data.converters

import ru.khiraevmalik.githubclientexample.data.model.NWGitHubOwner
import ru.khiraevmalik.githubclientexample.domain_model.GitHubOwner

object GitHubOwnerConverter {

    fun convert(owner: NWGitHubOwner) = GitHubOwner(
            owner.login,
            owner.avatar_url
    )

}
