package ru.khiraevmalik.githubclientexample.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NWGitHubOwner(
        val login: String,
        val avatar_url: String
)
