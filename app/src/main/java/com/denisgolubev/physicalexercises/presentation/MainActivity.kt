package com.denisgolubev.physicalexercises.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.denisgolubev.physicalexercises.presentation.composables.Screen
import com.denisgolubev.physicalexercises.ui.theme.PhysicalExercisesTheme


class MainActivity : ComponentActivity() {

    private val viewModel: StopwatchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PhysicalExercisesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF485A63)
                ) {
                    val state by viewModel.getState().collectAsState()
                    Screen(
                        state = state,
                        onClickStart = viewModel::startStopwatch,
                        onClickStop = viewModel::postStopwatchState,
                        onClickExercise = viewModel::onExerciseClick
                    )
                }
            }
        }
    }
}