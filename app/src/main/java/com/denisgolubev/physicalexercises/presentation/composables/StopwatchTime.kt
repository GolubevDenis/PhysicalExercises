package com.denisgolubev.physicalexercises.presentation.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.denisgolubev.physicalexercises.presentation.state.StopwatchState

@Composable
fun StopwatchTime(state: StopwatchState) {
    val title = state.exercise?.title.orEmpty()
    Column(
        modifier = Modifier
            .width(200.dp)
            .padding(vertical = 8.dp),
    ) {
        Text(text = title, color = Color.White, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(16.dp))
        TimerValue("м", state.min, 44.dp)
        TimerValue("с", state.sec, 74.dp)
        TimerValue("мс", state.millis, 90.dp)
    }
}

@Composable
private fun TimerValue(timeUnit: String, value: String, startPadding: Dp) {
    Row {
        Text(text = timeUnit, color = Color.LightGray, fontSize = 12.sp)
        Text(
            text = value,
            color = Color.White,
            fontSize = 22.sp,
            modifier = Modifier.padding(start = startPadding)
        )
    }
}
