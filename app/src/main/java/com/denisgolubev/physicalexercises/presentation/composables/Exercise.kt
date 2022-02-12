package com.denisgolubev.physicalexercises.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.denisgolubev.physicalexercises.presentation.state.ExerciseState

private val selectedExerciseMarkerColor = Color(0xFF6C9C45)

@Composable
fun Exercise(
    exercise: ExerciseState,
    onClickExercise: (ExerciseState) -> Unit
) {
    Card(
        Modifier
            .padding(vertical = 2.dp, horizontal = 2.dp)
            .height(80.dp)
            .clickable { onClickExercise(exercise) }
    ) {
        Box {
            if (exercise.isSelected) {
                Surface(
                    shape = CircleShape,
                    color = selectedExerciseMarkerColor,
                    modifier = Modifier
                        .padding(start = 4.dp, top = 4.dp)
                        .size(5.dp)
                ) {}
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = exercise.title,
                    fontSize = 10.sp,
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 12.dp),
                    textAlign = TextAlign.Center
                )
                Image(
                    painter = painterResource(id = exercise.image),
                    contentDescription = exercise.title,
                    modifier = Modifier
                        .padding(end = 32.dp, bottom = 4.dp, start = 8.dp)
                        .size(40.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}