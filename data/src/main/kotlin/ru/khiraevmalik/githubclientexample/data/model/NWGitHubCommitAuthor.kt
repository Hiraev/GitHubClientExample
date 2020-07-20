package ru.khiraevmalik.githubclientexample.data.model

import com.squareup.moshi.JsonClass
import java.time.LocalDateTime

@JsonClass(generateAdapter = true)
data class NWGitHubCommitAuthor(
        val name: String,
        val email: String,
        val date: LocalDateTime
)
