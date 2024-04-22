package com.example.movies.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity(tableName = "games")
@Serializable
data class Monster (

    @PrimaryKey(autoGenerate = false)
    var id: Int,
    var title: String,

    @SerialName(value="short_description")
    var shortDescription: String?,

    @SerialName("genre")
    var genre: String?,

    @SerialName(value="platform")
    var platform: String?,

    @SerialName(value="developer")
    var developer: String?,

    @SerialName(value="publisher")
    var publisher: String?,

    @SerialName(value="release_date")
    var release_date: String?,
)
