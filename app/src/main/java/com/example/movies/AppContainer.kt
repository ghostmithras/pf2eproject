package com.example.movies

import com.example.movies.network.MonsterApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import android.content.Context
import com.example.movies.data.AuthRepository
import com.example.movies.data.FirebaseAuthRepository
import com.example.movies.data.MonsterApiRepository
import com.example.movies.data.MonsterDatabase
import com.example.movies.data.MonsterStorageRepository
import com.example.movies.data.NetworkMonsterApiRepository
import com.example.movies.data.OfflineMonsterStorageRepository
import com.example.movies.network.FirebaseAuthService

interface AppContainer {
    val monsterApiRepository: MonsterApiRepository
    val monsterStorageRepository: MonsterStorageRepository
    val authRepository: AuthRepository
}

class DefaultAppContainer (private val context: Context): AppContainer {
    // add retrofit db
    private val baseUrl = "https://www.freetogame.com/api/games/"

    private val configuredJson = Json {
        isLenient = true
        coerceInputValues = true
        ignoreUnknownKeys = true
    }

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(configuredJson.asConverterFactory("application/json".toMediaType()))
        .build()

    private val retrofitService: MonsterApiService by lazy {
        retrofit.create(MonsterApiService:: class.java)
    }

    override val monsterApiRepository: MonsterApiRepository by lazy {
        NetworkMonsterApiRepository(retrofitService)
    }

    // add room db
    override val monsterStorageRepository: MonsterStorageRepository by lazy {
        OfflineMonsterStorageRepository(MonsterDatabase.getDatabase(context).monsterDao())
    }

    override val authRepository: FirebaseAuthRepository by lazy {
        FirebaseAuthRepository(FirebaseAuthService())
    }
}