package ru.khiraevmalik.githubclientexample.di_container

import ru.khiraevmalik.githubclientexample.data.MoshiFactory
import ru.khiraevmalik.githubclientexample.data.OkHttpFactory
import ru.khiraevmalik.githubclientexample.data.RetrofitFactory
import ru.khiraevmalik.githubclientexample.data.repositories.GitHubCommitsRepositoryImpl
import ru.khiraevmalik.githubclientexample.data.repositories.GitHubPublicReposRepositoryImpl
import ru.khiraevmalik.githubclientexample.domain.interactors.GitHubCommitsInteractorImpl
import ru.khiraevmalik.githubclientexample.domain.interactors.GitHubReposInteractorImpl
import ru.khiraevmalik.githubclientexample.interactors.GitHubCommitsInteractor
import ru.khiraevmalik.githubclientexample.interactors.GitHubReposInteractor

object DiDomainContainer {

    private val moshi by lazy {
        MoshiFactory.createMoshiForGitHubApi()
    }

    private val okHttpClient by lazy {
        OkHttpFactory.createOkHttp()
    }

    private val retrofit by lazy {
        RetrofitFactory.createForGitHub(moshi, okHttpClient)
    }

    private val gitHubApi by lazy {
        RetrofitFactory.createGitHubApi(retrofit)
    }

    private val gitHubReposRepository by lazy {
        GitHubPublicReposRepositoryImpl(gitHubApi)
    }

    private val gitHubCommitsRepository by lazy {
        GitHubCommitsRepositoryImpl(gitHubApi)
    }

    fun provideCommitsInteractor(): GitHubCommitsInteractor = GitHubCommitsInteractorImpl(gitHubCommitsRepository)

    fun provideReposInteractor(): GitHubReposInteractor = GitHubReposInteractorImpl(gitHubReposRepository)

}
