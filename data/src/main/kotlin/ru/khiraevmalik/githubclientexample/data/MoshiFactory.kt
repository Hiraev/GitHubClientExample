package ru.khiraevmalik.githubclientexample.data

import com.squareup.moshi.Moshi

object MoshiFactory {

    fun createMoshiForGitHubApi(): Moshi = Moshi.Builder()
            .build()

}
