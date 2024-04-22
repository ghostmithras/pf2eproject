package com.example.movies.data

import com.example.movies.model.MonsterUser
import com.example.movies.network.FirebaseAuthService

interface AuthRepository {
    fun createAccount(email: String, password: String, onResult: (Throwable?) -> Unit)
    fun authenticate(email: String, password: String, onResult: (Throwable?) -> Unit)
    fun getCurrentUser(): MonsterUser
    fun logoutUser()
}

class FirebaseAuthRepository (private val _authService: FirebaseAuthService): AuthRepository {

    override fun createAccount(email: String, password: String, onResult: (Throwable?) -> Unit) {
        _authService.createAccount(email, password, onResult)
    }

    override fun authenticate(email: String, password: String, onResult: (Throwable?) -> Unit) {
        _authService.authenticate(email, password, onResult)
    }
    override fun getCurrentUser(): MonsterUser {
        val firebaseUser = _authService.getCurrentUser()
        if(firebaseUser == null) {
            throw Exception("User credentials not found")
        } else {
            return MonsterUser(
                email = firebaseUser.email ?: "",
                id = firebaseUser.uid,
                isLoggedIn = firebaseUser.email != null && firebaseUser.uid != null
            )
        }
    }

    override fun logoutUser() {
        _authService.logout()
    }
}