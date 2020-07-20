package ru.khiraevmalik.githubclientexample.utils

fun String.shortSha(): String {
    if (this.length <= 7) return this
    return substring(0, 7)
}
