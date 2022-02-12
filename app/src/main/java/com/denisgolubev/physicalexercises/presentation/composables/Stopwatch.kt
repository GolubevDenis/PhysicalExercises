package com.denisgolubev.physicalexercises.presentation.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.denisgolubev.physicalexercises.presentation.state.StopwatchState

private val background = Color(0xFF2E3940)

@Composable
fun Stopwatch(
    modifier: Modifier = Modifier,
    state: StopwatchState,
    onClickStart: () -> Unit,
    onClickStop: () -> Unit
) {
    Surface(
        color = background,
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            StopwatchTime(state)
            Box(
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                StopwatchButton(
                    running = state.isRunning,
                    onClickStart = onClickStart,
                    onClickStop = onClickStop
                )
            }
        }
    }
}