package ru.khiraevmalik.githubclientexample.domain_model

data class GitHubCommitInfo(
        val sha: String,
        val date: String,
        val message: String,
        val authorName: String,
        val authorEmail: String,
        val parents: List<String>
)
