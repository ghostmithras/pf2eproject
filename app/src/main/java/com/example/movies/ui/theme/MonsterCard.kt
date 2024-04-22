package com.example.movies.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movies.model.Monster

@Composable
fun MonsterCard (
    modifier: Modifier = Modifier,
    monsterItem: Monster
)
{
    Column(
        modifier = Modifier
            .border(1.dp, Color(0xFF951019), shape = RectangleShape)
            .padding(5.dp)
    ) {
        Row (
            modifier = Modifier
                .background(color = Color(0xFFf8f6d3))
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            Row(
                modifier = Modifier.padding(20.dp)
            ) {
                monsterItem.title?.let{
                    Text(
                        color = Color(0xFF0a0302),
                        text = it,
                        modifier = Modifier.padding(top = 8.dp, end = 8.dp),
                        style = TextStyle(fontSize = 14.sp),
                        maxLines = 1
                    )
                }

                monsterItem.genre?.let{
                    Text(
                        color = Color(0xFF0a0302),
                        text = it,
                        modifier = Modifier.padding(top = 8.dp, end = 8.dp),
                        style = TextStyle(fontSize = 14.sp),
                        maxLines = 1
                    )
                }

                monsterItem.platform?.let{
                    Text(
                        color = Color(0xFF0a0302),
                        text = it,
                        modifier = Modifier.padding(top = 8.dp, end = 8.dp),
                        style = TextStyle(fontSize = 14.sp),
                        maxLines = 1
                    )
                }

                monsterItem.shortDescription?.let{
                    Text(
                        color = Color(0xFF0a0302),
                        text = it,
                        modifier = Modifier.padding(top = 8.dp, end = 8.dp),
                        style = TextStyle(fontSize = 14.sp),
                        maxLines = 1
                    )
                }

                monsterItem.release_date?.let{
                    Text(
                        color = Color(0xFF0a0302),
                        text = it,
                        modifier = Modifier.padding(top = 8.dp, end = 8.dp),
                        style = TextStyle(fontSize = 14.sp),
                        maxLines = 1
                    )
                }
            }
        }
    }

}