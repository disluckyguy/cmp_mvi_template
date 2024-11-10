package io.github.mostafa.mvitemplate

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import io.github.mostafa.mvitemplate.theme.AppTheme
import io.github.mostafa.mvitemplate.timer.timerModule
import io.github.mostafa.mvitemplate.timer.view.TimerPage
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication


@Composable
@Preview
fun App() {

    KoinApplication(application = {
        modules(timerModule)
    })  {

        AppTheme {
            Scaffold {
                TimerPage()
            }
        }
    }

}