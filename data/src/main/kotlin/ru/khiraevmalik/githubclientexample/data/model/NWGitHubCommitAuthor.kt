package ru.khiraevmalik.githubclientexample.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NWGitHubCommitAuthor(
        val name: String,
        val email: String,
        val date: String
)
