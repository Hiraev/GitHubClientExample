package ru.khiraevmalik.githubclientexample.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NWGitHubCommitInfo(
        val message: String,
        val author: NWGitHubCommitAuthor
)
