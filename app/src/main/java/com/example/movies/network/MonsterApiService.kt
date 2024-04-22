package com.example.movies.network

import com.example.movies.model.MonsterData
import retrofit2.http.GET

private const val FREE_GAMES_ENDPOINT = "https://www.freetogame.com/api/games/"

interface MonsterApiService {
    @GET(FREE_GAMES_ENDPOINT)
    suspend fun getMonsters(
    ): MonsterData
}
