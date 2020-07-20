package ru.khiraevmalik.githubclientexample.data.converters

import ru.khiraevmalik.githubclientexample.data.model.NWGitHubCommit
import ru.khiraevmalik.githubclientexample.domain_model.GitHubCommitInfo

object GitHubCommitsConverter {

    fun convert(commit: NWGitHubCommit) = GitHubCommitInfo(
            message = commit.commit.message,
            authorName = commit.commit.author.name,
            authorEmail = commit.commit.author.email
    )

}
