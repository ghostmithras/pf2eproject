package com.example.movies.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.movies.model.Monster

@Dao
interface MonsterDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(monster: Monster)

    @Update
    suspend fun update(monster: Monster)

    @Delete
    suspend fun delete(monster: Monster)

    @Query("SELECT * FROM games ORDER BY title DESC")
    suspend fun getAllMonsters(): List<Monster>

    @Query("DELETE FROM games")
    suspend fun clearAllMonsters()
}