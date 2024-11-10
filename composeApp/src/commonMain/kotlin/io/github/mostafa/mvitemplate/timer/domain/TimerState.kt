package io.github.mostafa.mvitemplate.timer.domain

import kotlin.time.Duration

sealed class TimerState(val duration: Int)

class TimerInitial(duration: Int) : TimerState(duration) {

    override fun toString(): String {
        return "TimerInitial {duration: $duration}"
    }
}

class TimerInProgress(duration: Int) : TimerState(duration) {

    override fun toString(): String {
        return "TimerInProgress {duration: $duration}"
    }
}

class TimerPaused(duration: Int) : TimerState(duration) {

    override fun toString(): String {
        return "TimerPaused {duration: $duration}"
    }
}

class TimerFinished : TimerState(0) {

    override fun toString(): String {
        return "TimerFinished {duration: $duration}"
    }
}