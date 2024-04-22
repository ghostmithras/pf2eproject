package com.example.movies.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movies.model.Monster
import kotlinx.coroutines.InternalCoroutinesApi

@Database(entities = [Monster::class], version = 2, exportSchema = false)
abstract class MonsterDatabase: RoomDatabase() {
    abstract fun monsterDao(): MonsterDao

    companion object {
        @Volatile
        private var Instance: MonsterDatabase? = null

        @OptIn(InternalCoroutinesApi::class)
        fun getDatabase(context: Context): MonsterDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, MonsterDatabase::class.java, "monster_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}

