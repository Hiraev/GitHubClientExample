package ru.khiraevmalik.githubclientexample.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NWCommitInfo(
        val message: String,
        val author: NWCommitAuthor
)
