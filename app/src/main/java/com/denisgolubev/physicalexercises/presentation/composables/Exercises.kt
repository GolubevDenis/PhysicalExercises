package com.denisgolubev.physicalexercises.presentation.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.denisgolubev.physicalexercises.presentation.state.ExerciseState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Exercises(
    modifier: Modifier = Modifier,
    exercises: List<ExerciseState>,
    onClickExercise: (ExerciseState) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier,
        cells = GridCells.Adaptive(minSize = 60.dp)
    ) {
        items(
            count = exercises.size,
            itemContent = { index ->
                Exercise(exercises[index]) {
                    onClickExercise(exercises[index])
                }
            }
        )
    }
}