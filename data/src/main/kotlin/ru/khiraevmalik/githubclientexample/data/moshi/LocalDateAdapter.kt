package ru.khiraevmalik.githubclientexample.data.moshi

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class LocalDateAdapter {

    @FromJson
    fun fromJson(json: String): LocalDateTime =
            LocalDateTime.ofInstant(Instant.from(DateTimeFormatter.ISO_INSTANT.parse(json)), ZoneId.systemDefault())

    @ToJson
    fun toJson(localDateTime: LocalDateTime) = localDateTime.toString()

}
