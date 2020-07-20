package ru.khiraevmalik.githubclientexample.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NWGitHubRepositoryInfo(
        val id: Int,
        val full_name: String,
        val owner: NWGitHubOwner,
        val commits_url: String
)
