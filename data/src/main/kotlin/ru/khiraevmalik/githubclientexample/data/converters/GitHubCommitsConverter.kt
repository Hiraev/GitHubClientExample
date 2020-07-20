package ru.khiraevmalik.githubclientexample.data.converters

import ru.khiraevmalik.githubclientexample.data.model.NWGitHubCommit
import ru.khiraevmalik.githubclientexample.data.model.NWGitHubCommitParent
import ru.khiraevmalik.githubclientexample.domain_model.GitHubCommitInfo
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object GitHubCommitsConverter {

    private val dateFormatter = DateTimeFormatter.ofPattern("dd.MM.YYYY")

    fun convert(commit: NWGitHubCommit) = GitHubCommitInfo(
            sha = commit.sha,
            date = convertDateTime(commit.commit.author.date),
            message = commit.commit.message,
            authorName = commit.commit.author.name,
            authorEmail = commit.commit.author.email,
            parents = commit.parents.map(NWGitHubCommitParent::sha)
    )

    private fun convertDateTime(localDateTime: LocalDateTime) = localDateTime.format(dateFormatter)

}
