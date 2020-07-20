package ru.khiraevmalik.githubclientexample.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NWCommit(
        val sha: String,
        val commit: NWCommitInfo
)
