package com.example.movies.screens.fulltable

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.movies.BestiaryApplication
import com.example.movies.data.AuthRepository
import com.example.movies.data.MonsterApiRepository
import com.example.movies.data.MonsterStorageRepository
import com.example.movies.model.Monster
import com.example.movies.model.MonsterUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class MonsterUiState (
    val monsters: List<Monster> = mutableListOf()
)

class MonsterViewModel(
    private val monsterApiRepository: MonsterApiRepository,
    private val monsterStorageRepository: MonsterStorageRepository,
    private val authRepository: AuthRepository
): ViewModel() {
    private val _monsterUiState = MutableStateFlow(MonsterUiState())
    val uiState: StateFlow<MonsterUiState> = _monsterUiState.asStateFlow()

    init{
        getTrendingMovies()
    }

    fun getUser() {
        val user: MonsterUser = authRepository.getCurrentUser()
        Log.d("CURRENT USER", user.toString())
    }

    private suspend fun clearMonsters() {
        monsterStorageRepository.clearAllMonsters()
    }

    private suspend fun saveMonsters() {
        _monsterUiState.value.monsters.forEach {
                monster ->
            monsterStorageRepository.insertMonster(monster)
        }
    }


    private fun getTrendingMovies() {
        viewModelScope.launch {
            try {
                var listResult: List<Monster> = monsterApiRepository.getMonsters()

                _monsterUiState.update {
                        currentState ->
                    currentState.copy(monsters = listResult)
                }
                clearMonsters()
                saveMonsters()
            } catch (e: Exception) {
                Log.w("OFFLINE", "Service offline")
                var listResult = monsterStorageRepository.getAllMonsters()
                _monsterUiState.update {
                        currentState ->
                    currentState.copy(monsters = listResult)
                }
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BestiaryApplication)
                val monsterApiRepository = application.container.monsterApiRepository
                val monsterStorageRepository = application.container.monsterStorageRepository
                MonsterViewModel(
                    monsterApiRepository = monsterApiRepository,
                    monsterStorageRepository = monsterStorageRepository,
                    authRepository = application.container.authRepository
                )
            }
        }
    }
}