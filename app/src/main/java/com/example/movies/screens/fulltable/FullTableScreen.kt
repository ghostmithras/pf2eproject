package com.example.movies.screens.fulltable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.movies.ui.theme.MonsterCard

@Composable
fun FullTableScreen(
    modifier: Modifier = Modifier,
    monsterViewModel: MonsterViewModel
) {
    val monsterUiState = monsterViewModel.uiState.collectAsState()

    Column () {
        Row(
            horizontalArrangement = Arrangement.spacedBy(30.dp)
        )
        {
            Text("")
            Text("Name")
            Text("|")
            Text("Level")
            Text("|")
            Text("Families")
            Text("|")
            Text("Types")
        }
        LazyColumn{
            items(monsterUiState.value.monsters) {
                    monster ->
                MonsterCard(monsterItem = monster)
            }
        }
    }

}