package io.github.mostafa.mvitemplate.timer.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.material.icons.rounded.Pause
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.RestartAlt
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.mostafa.mvitemplate.timer.domain.TimerFinished
import io.github.mostafa.mvitemplate.timer.domain.TimerInProgress
import io.github.mostafa.mvitemplate.timer.domain.TimerInitial
import io.github.mostafa.mvitemplate.timer.domain.TimerPause
import io.github.mostafa.mvitemplate.timer.domain.TimerPaused
import io.github.mostafa.mvitemplate.timer.domain.TimerReset
import io.github.mostafa.mvitemplate.timer.domain.TimerResume
import io.github.mostafa.mvitemplate.timer.domain.TimerStarted
import io.github.mostafa.mvitemplate.timer.domain.TimerViewModel
import org.koin.compose.viewmodel.koinViewModel
import kotlin.time.Duration.Companion.minutes

@Composable
fun TimerPage(
    modifier: Modifier = Modifier,
    viewModel: TimerViewModel = koinViewModel<TimerViewModel>()
) {
    Scaffold {
        Box {
            Column(
                modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                TimerText(modifier = modifier.padding(12.dp))
                Actions()
            }
        }
    }
}

@Composable
fun TimerText(
    modifier: Modifier = Modifier,
    viewModel: TimerViewModel = koinViewModel<TimerViewModel>()
) {
    val state = viewModel.uiState.collectAsState()
    val duration = state.value.duration
    val minuteStr = ((duration / 60) % 60).toString().padStart(2, '0')
    val secondStr = (duration % 60).toString().padStart(2, '0')

    Text(
        text = "$minuteStr:$secondStr",
        style = MaterialTheme.typography.displayLarge,
    )

}

@Composable
fun Actions(
    modifier: Modifier = Modifier,
    viewModel: TimerViewModel = koinViewModel<TimerViewModel>()
) {
    val state = viewModel.uiState.collectAsState()
    Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
        when (state.value) {
            is TimerFinished -> FloatingActionButton(onClick = {
                viewModel.update(TimerReset)
            }) {
                Icon(
                    Icons.Filled.RestartAlt,
                    contentDescription = "Restart Timer"
                )
            }
            is TimerInProgress -> {
                FloatingActionButton(onClick = {
                    viewModel.update(TimerPause)
                }) {
                    Icon(
                        Icons.Rounded.Pause,
                        contentDescription = "Pause Timer"
                    )
                }
                FloatingActionButton(onClick = {
                    viewModel.update(TimerReset)
                }) {
                    Icon(
                        Icons.Rounded.RestartAlt,
                        contentDescription = "Reset Timer"
                    )
                }
            }
            is TimerInitial -> FloatingActionButton(onClick = {
                viewModel.update(TimerStarted(state.value.duration))
            }) {
                Icon(
                    Icons.Filled.PlayArrow,
                    contentDescription = "Start Timer"
                )
            }

            is TimerPaused -> {
                FloatingActionButton(onClick = {
                    viewModel.update(TimerResume)
                }) {
                    Icon(
                        Icons.Rounded.PlayArrow,
                        contentDescription = "Resume Timer"
                    )
                }
                FloatingActionButton(onClick = {
                    viewModel.update(TimerReset)
                }) {
                    Icon(
                        Icons.Rounded.RestartAlt,
                        contentDescription = "Reset Timer"
                    )
                }
            }
        }
    }
}