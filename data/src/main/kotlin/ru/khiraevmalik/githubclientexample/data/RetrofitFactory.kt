package ru.khiraevmalik.githubclientexample.data

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.khiraevmalik.githubclientexample.data.api.Api
import ru.khiraevmalik.githubclientexample.data.api.GitHubApi

object RetrofitFactory {

    fun createForGitHub(moshi: Moshi, okHttpClient: OkHttpClient) = Retrofit.Builder()
            .baseUrl(Api.baseUrl)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    fun createGitHubApi(retrofit: Retrofit) = retrofit.create(GitHubApi::class.java)

}
