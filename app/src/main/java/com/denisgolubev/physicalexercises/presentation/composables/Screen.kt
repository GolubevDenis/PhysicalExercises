package com.denisgolubev.physicalexercises.presentation.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.denisgolubev.physicalexercises.presentation.state.ExerciseState
import com.denisgolubev.physicalexercises.presentation.state.ScreenState

@Composable
fun Screen(
    state: ScreenState,
    onClickStart: () -> Unit,
    onClickStop: () -> Unit,
    onClickExercise: (ExerciseState) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Exercises(
            modifier = Modifier.weight(5f),
            exercises = state.exercises,
            onClickExercise = onClickExercise
        )
        Stopwatch(
            modifier = Modifier.weight(2f),
            state = state.stopwatchState,
            onClickStart = onClickStart,
            onClickStop = onClickStop
        )
    }
}