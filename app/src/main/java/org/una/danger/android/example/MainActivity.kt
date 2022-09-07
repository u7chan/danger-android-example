package org.una.danger.android.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import org.una.danger.android.example.ui.theme.DangerandroidexampleTheme
import org.una.danger.android.example.usecase.CounterUseCaseImpl
import org.una.danger.android.example.view.CounterView
import org.una.danger.android.example.view.CounterViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DangerandroidexampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CounterView(injectViewModel())
                }
            }
        }
    }
}

private fun injectViewModel(): CounterViewModel {
    return CounterViewModel(CounterUseCaseImpl())
}

private fun WarnMethod() {
    val optionalValue: Int? = 1234
    println(optionalValue!! + 1)
    val unusedValue = "hoge"
}