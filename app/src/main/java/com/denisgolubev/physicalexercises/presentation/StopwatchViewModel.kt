package com.denisgolubev.physicalexercises.presentation

import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denisgolubev.physicalexercises.R
import com.denisgolubev.physicalexercises.presentation.state.ExerciseState
import com.denisgolubev.physicalexercises.presentation.state.ScreenState
import com.denisgolubev.physicalexercises.presentation.state.StopwatchState
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StopwatchViewModel : ViewModel() {

    private var stopwatchMillis = 0L
    private var stopwatchJob: Job? = null
    private val state = MutableStateFlow(ScreenState.EMPTY_STATE)

    fun getState(): StateFlow<ScreenState> = state

    init {
        viewModelScope.launch {
            fillWithExercises()
        }
    }

    fun startStopwatch() {
        fun postZeroStopwatchForExercise() {
            val selectedExercise = state.value.exercises.first { it.isSelected }
            val stopwatchState = StopwatchState(selectedExercise)
            val newState = state.value.copy(stopwatchState = stopwatchState)
            postState(newState)
        }

        stopwatchMillis = 0
        postZeroStopwatchForExercise()

        stopwatchJob = viewModelScope.launch {
            while (true) {
                delay(STOPWATCH_STEP_IN_MILLIS)
                val newState = state.value.incrementTime(STOPWATCH_STEP_IN_MILLIS)
                postState(newState)
            }
        }
    }

    fun postStopwatchState() {
        stopwatchMillis = 0
        stopwatchJob?.cancel()
        stopwatchJob = null
        postStopwatchState(state.value)
    }

    fun onExerciseClick(exercise: ExerciseState) {
        val newState = state.value.selectExercise(exercise)
        postState(newState)
    }

    private fun fillWithExercises() {
        val newState = state.value.copy(exercises = EXERCISES)
        postState(newState)
    }

    private fun postStopwatchState(state: ScreenState) {
        val newState = state.copy(
            stopwatchState = state.stopwatchState.copy(
                min = "00",
                sec = "00",
                millis = "00",
                isRunning = false
            )
        )
        postState(newState)
    }

    private fun ScreenState.incrementTime(delta: Long): ScreenState {
        stopwatchMillis += delta
        val minutes = stopwatchMillis / 60000
        val seconds = (stopwatchMillis / 1000) - minutes
        val millis = stopwatchMillis % 1000

        val minutesText = minutes.toString().padStart(2, '0')
        val secondsText = seconds.toString().padStart(2, '0')
        val millisText = millis.toString().padStart(2, '0').take(2)

        return this.copy(
            stopwatchState = this.stopwatchState.copy(
                min = minutesText,
                sec = secondsText,
                millis = millisText,
                isRunning = true
            )
        )
    }

    private fun ScreenState.selectExercise(exercise: ExerciseState): ScreenState {
        return this.copy(
            exercises = this.exercises.map {
                val isSelected = exercise.title == it.title
                it.copy(isSelected = isSelected)
            }
        )
    }

    private fun postState(newState: ScreenState) {
        viewModelScope.launch { state.emit(newState) }
    }

    private companion object {
        const val STOPWATCH_STEP_IN_MILLIS = 10L
        val EXERCISES = (generateExercises(
            "Скручивания",
            R.drawable.icons_sit_ups
        ).mapIndexed { index, exerciseState ->
            if (index == 0) {
                exerciseState.copy(isSelected = true)
            } else {
                exerciseState
            }
        } +
                generateExercises("Канаты", R.drawable.icons_ropes) +
                generateExercises("Планка", R.drawable.icons_plank) +
                generateExercises("Бег", R.drawable.icons_run) +
                generateExercises("Приседания", R.drawable.icons_squats) +
                generateExercises("Растяжка", R.drawable.icons_stretching))
            .shuffled()

        private fun generateExercises(
            title: String,
            @DrawableRes imageRes: Int,
            count: Int = 50
        ): List<ExerciseState> {
            return (0..count).map {
                ExerciseState(
                    "$title [${it + 1}]",
                    imageRes
                )
            }
        }
    }

}