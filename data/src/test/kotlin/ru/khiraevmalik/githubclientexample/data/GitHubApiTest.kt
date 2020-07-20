package ru.khiraevmalik.githubclientexample.data

import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class GitHubApiTest {

    private val moshi = MoshiFactory.createMoshiForGitHubApi()
    private val okhttp = OkHttpFactory.createOkHttp()
    private val retrofit = RetrofitFactory.createForGitHub(moshi, okhttp)

    private val gitHubApi = RetrofitFactory.createGitHubApi(retrofit)

    @Test
    fun fetchRepositories() {
        runBlocking {
            val res = gitHubApi.fetchPublicRepositories()
            val sinceId = res[res.lastIndex - 1].id
            val secondQueryFirstId = gitHubApi.fetchPublicRepositories(sinceId).first().id
            Assert.assertEquals(res.last().id, secondQueryFirstId)
        }
    }

    @Test
    fun fetchCommit() {
        runBlocking {
            val res = gitHubApi.fetchPublicRepositories().last()
            gitHubApi.fetchCommits(res.full_name)
        }
    }

}
