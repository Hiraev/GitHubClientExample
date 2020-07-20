package ru.khiraevmalik.githubclientexample.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NWGitHubCommit(
        val sha: String,
        val commit: NWGitHubCommitInfo
)
