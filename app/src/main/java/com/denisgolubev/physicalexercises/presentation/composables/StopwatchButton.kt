package com.denisgolubev.physicalexercises.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.denisgolubev.physicalexercises.R

@Composable
fun StopwatchButton(
    modifier: Modifier = Modifier,
    running: Boolean,
    onClickStart: () -> Unit,
    onClickStop: () -> Unit
) {
    val imageRes = if (running) R.drawable.icon_pause else R.drawable.icon_play
    Image(
        painter = painterResource(id = imageRes),
        contentDescription = "Play Button",
        modifier = modifier
            .padding(end = 32.dp)
            .size(40.dp)
            .clickable {
                if (running) {
                    onClickStop()
                } else {
                    onClickStart()
                }
            }
    )
}