package ru.khiraevmalik.githubclientexample.domain_model

data class GitHubRepository(
        val fullName: String,
        val owner: GitHubOwner,
        val commitsUrl: String
)
