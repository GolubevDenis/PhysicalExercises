package com.denisgolubev.physicalexercises.presentation.state

import androidx.annotation.DrawableRes

data class ScreenState(
    val exercises: List<ExerciseState>,
    val stopwatchState: StopwatchState
) {

    companion object {
        val EMPTY_STATE = ScreenState(
            exercises = emptyList(),
            stopwatchState = StopwatchState.EMPTY_STATE
        )
    }
}

data class ExerciseState(
    val title: String,
    @DrawableRes val image: Int,
    val isSelected: Boolean = false
)

data class StopwatchState(
    val exercise: ExerciseState?,
    val min: String = "00",
    val sec: String = "00",
    val millis: String = "00",
    val isRunning: Boolean = false
) {
    companion object {
        val EMPTY_STATE = StopwatchState(exercise = null)
    }
}