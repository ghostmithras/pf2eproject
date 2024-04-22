package com.example.movies.data

import com.example.movies.model.Monster

interface MonsterStorageRepository {
    // flow obviates suspend
    suspend fun getAllMonsters(): List<Monster>
    suspend fun insertMonster(monster: Monster)
    suspend fun deleteMonster(monster: Monster)
    suspend fun updateMonster(monster: Monster)
    suspend fun clearAllMonsters()
}

class OfflineMonsterStorageRepository(private val monsterDao: MonsterDao): MonsterStorageRepository {
    override suspend fun getAllMonsters(): List<Monster> {
        return monsterDao.getAllMonsters()
    }

    override suspend fun clearAllMonsters() {
        monsterDao.clearAllMonsters()
    }

    override suspend fun insertMonster(monster: Monster) {
        monsterDao.insert(monster)
    }

    override suspend fun deleteMonster(monster: Monster) {
        monsterDao.delete(monster)
    }

    override suspend fun updateMonster(monster: Monster) {
        monsterDao.update(monster)
    }
}