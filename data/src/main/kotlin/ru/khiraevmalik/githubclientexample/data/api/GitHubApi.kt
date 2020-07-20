package ru.khiraevmalik.githubclientexample.data.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.khiraevmalik.githubclientexample.data.model.NWGitHubCommit
import ru.khiraevmalik.githubclientexample.data.model.NWGitHubRepositoryInfo

interface GitHubApi {

    @GET(Api.Path.repositories)
    suspend fun fetchPublicRepositories(
            @Query(Api.Params.since) since: Int? = null
    ): List<NWGitHubRepositoryInfo>

    @GET(Api.Path.commits)
    suspend fun fetchCommits(
            @Path(Api.Path.Value.repos, encoded = true) repoFullName: String
    ): List<NWGitHubCommit>

}
