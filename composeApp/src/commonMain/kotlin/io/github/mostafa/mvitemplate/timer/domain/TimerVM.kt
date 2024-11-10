package io.github.mostafa.mvitemplate.timer.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.mostafa.mvitemplate.timer.Ticker
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TimerViewModel : ViewModel() {

    private val duration = 60

    private val _uiState = MutableStateFlow<TimerState>(TimerInitial(duration))
    val uiState = _uiState.asStateFlow()
    private val ticker = Ticker()

    private var tickJob: Job? = null

    fun update(event: TimerEvent) {
        when (event) {
            is TimerPause -> onPause(event)
            is TimerReset -> onReset(event)
            is TimerResume -> onResume(event)
            is TimerStarted -> onStarted(event)
            is TimerTicked -> onTicked(event)
        }
    }

    private fun onStarted(event: TimerStarted) {
        tickJob?.cancel()
        tickJob = viewModelScope.launch {
                ticker.tick(event.duration).collect {
                update(TimerTicked(it))
            }
        }
        _uiState.update { TimerInProgress(event.duration) }
    }

    private fun onPause(event: TimerPause) {
        if (_uiState.value is TimerInProgress) {
            ticker.pause()
            _uiState.update {
                TimerPaused(it.duration)
            }
        }
    }

    private fun onResume(event: TimerResume) {
        if (_uiState.value is TimerPaused) {
            ticker.resume()
            _uiState.update {
                TimerInProgress(it.duration)
            }
        }
    }

    private fun onReset(event: TimerReset) {
        tickJob?.cancel()
        _uiState.update {
            TimerInitial(duration)
        }
    }

    private fun onTicked(event: TimerTicked) {
        if (event.duration == uiState.value.duration) {
            return
        }
        _uiState.update {
            if (event.duration > 0) {
                TimerInProgress(event.duration)
            } else {
                TimerFinished()
            }
        }

    }
}

