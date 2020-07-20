package ru.khiraevmalik.githubclientexample.data

import com.squareup.moshi.Moshi
import ru.khiraevmalik.githubclientexample.data.moshi.LocalDateAdapter

object MoshiFactory {

    fun createMoshiForGitHubApi(): Moshi = Moshi.Builder()
            .add(LocalDateAdapter())
            .build()

}
