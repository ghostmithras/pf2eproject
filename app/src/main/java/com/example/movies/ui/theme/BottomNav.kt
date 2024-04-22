package com.example.movies.ui.theme

import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movies.Destination
import com.example.movies.R

@Composable
fun BottomNav(navController: NavController, modifier: Modifier = Modifier) {
    BottomNavigation (elevation = 7.dp) {
        val navBackStackEntry = navController.currentBackStackEntry
        val currentDestination = navBackStackEntry?.destination

        BottomNavigationItem(
            selected = currentDestination?.route == Destination.FullTable.route,
            onClick = {
                navController.navigate(Destination.FullTable.route) {
                    popUpTo(Destination.FullTable.route)
                }
            },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.home),
                    contentDescription = "Full Table",
                    modifier = Modifier.size(26.dp)
                )
            },
            label = { Text("Monsters")}
        )

        BottomNavigationItem(
            selected = currentDestination?.route == Destination.Levels.route,
            onClick = {
                navController.navigate(Destination.Levels.route) {
                    popUpTo(Destination.Levels.route)
                }
            },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.level),
                    contentDescription = "Levels",
                    modifier = Modifier.size(26.dp)
                )
            },
            label = { Text(text = Destination.Levels.route.replaceFirstChar {it.uppercase() })}
        )

        BottomNavigationItem(
            selected = currentDestination?.route == Destination.Families.route,
            onClick = {
                navController.navigate(Destination.Families.route) {
                    popUpTo(Destination.Families.route)
                }
            },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.family),
                    contentDescription = "Families",
                    modifier = Modifier.size(26.dp)
                )
            },
            label = { Text(text = Destination.Families.route.replaceFirstChar {it.uppercase() })}
        )

        BottomNavigationItem(
            selected = false,
            onClick = {
                navController.navigate(Destination.Types.route) {
                    popUpTo(Destination.Types.route)
                }
            },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.type),
                    contentDescription = "Types",
                    modifier = Modifier.size(26.dp)
                )
            },
            label = { Text(text = Destination.Types.route.replaceFirstChar {it.uppercase() })}
        )

        BottomNavigationItem(
            selected = false,
            onClick = {
                navController.navigate(Destination.SignIn.route) {
                    popUpTo(Destination.SignIn.route)
                }
            },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.account),
                    contentDescription = "Account",
                    modifier = Modifier.size(26.dp)
                )
            },
            label = { Text("Account")}
        )

    }
}