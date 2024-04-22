package com.example.movies

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movies.screens.families.FamiliesScreen
import com.example.movies.ui.theme.BottomNav
import com.example.movies.screens.fulltable.FullTableScreen
import com.example.movies.screens.fulltable.MonsterViewModel
import com.example.movies.ui.theme.MonstersTheme
import com.example.movies.screens.signin.SignInScreen
import com.example.movies.screens.signin.SignInViewModel
import com.example.movies.screens.levels.LevelsScreen
import com.example.movies.screens.types.TypesScreen

sealed class Destination (val route: String) {
    object FullTable: Destination("fulltable")
    object Levels: Destination("levels")
    object Families: Destination("families")
    object Search: Destination("search")
    object Types: Destination("types")
    object SignIn: Destination("signin")
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MonstersTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // controller
                    val navController = rememberNavController()
                    MonsterScaffold(navController)
                }
            }
        }
    }
}

@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun MonsterScaffold(navController: NavHostController, modifier: Modifier = Modifier) {
    Scaffold(
        bottomBar = {
            BottomNav(navController = navController)
        }
    ) {
            paddingValues ->

        val monsterViewModel: MonsterViewModel = viewModel(factory = MonsterViewModel.Factory)
        val signinViewModel: SignInViewModel = viewModel(factory = SignInViewModel.Factory)

        signinViewModel.navigateOnSignIn = {
            signinViewModel.uiState.value = signinViewModel.uiState.value.copy(
                email = "",
                password = "",
                errorMessage = ""
            )
            navController.navigate(Destination.FullTable.route)
        }

        NavHost(navController = navController, startDestination = Destination.FullTable.route) {
            composable(Destination.FullTable.route) {
                FullTableScreen(monsterViewModel = monsterViewModel)
            }

            composable(Destination.SignIn.route) {
                SignInScreen(signInViewModel = signinViewModel)
            }

            composable(Destination.Levels.route) {
                LevelsScreen()
            }

            composable(Destination.Types.route) {
                TypesScreen()
            }

            composable(Destination.Families.route) {
                FamiliesScreen()
            }
        }
    }
}



