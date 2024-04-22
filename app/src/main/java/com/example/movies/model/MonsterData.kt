package com.example.movies.model

import kotlinx.serialization.Serializable

@Serializable
data class MonsterData (
    var results: List<Monster>,
)