package io.github.mostafa.mvitemplate.timer

import io.github.mostafa.mvitemplate.timer.domain.TimerViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val timerModule = module {
    viewModel { TimerViewModel() }
}