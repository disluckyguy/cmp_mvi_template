package io.github.mostafa.mvitemplate.timer.domain

sealed class TimerEvent

data class TimerTicked(val duration: Int) : TimerEvent()

data object TimerPause : TimerEvent()

data object TimerResume : TimerEvent()

data object TimerReset : TimerEvent()

data class TimerStarted(val duration: Int) : TimerEvent()