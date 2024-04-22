package com.example.movies.data

import com.example.movies.model.Monster
import com.example.movies.network.MonsterApiService

interface MonsterApiRepository {
    suspend fun getMonsters(): List<Monster>
}

class NetworkMonsterApiRepository(
    // pass in any type of API service (such as retrofit or fake)
    private val monsterApiService: MonsterApiService
): MonsterApiRepository {
    override suspend fun getMonsters(): List<Monster> {
        return monsterApiService.getMonsters().results
    }
}