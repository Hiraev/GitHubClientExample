package ru.khiraevmalik.githubclientexample.data.api

object Api {

    const val baseUrl = "https://api.github.com/"

    object Path {
        const val repositories = "repositories"
        const val commits = "repos/{${Value.repos}}/commits"

        object Value {
            const val repos = "repos"
        }
    }

    object Params {
        const val since = "since"
    }

}
