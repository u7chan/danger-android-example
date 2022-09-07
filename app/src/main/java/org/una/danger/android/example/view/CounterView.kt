package org.una.danger.android.example.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.una.danger.android.example.ui.theme.DangerandroidexampleTheme

@Composable
fun CounterView(viewModel: CounterViewModel) {
    val uiState = viewModel.uiState.collectAsState()
    val uiEvent = viewModel.uiEvent.collectAsState(CounterViewModel.UiEvent.Idle)
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "CounterApp")
            })
        }
    ) { padding ->
        Box(
            Modifier
                .padding(padding)
                .fillMaxWidth()
        ) {
            CounterViewLayout(
                count = uiState.value.count,
                onIncrement = {
                    viewModel.incrementCount()
                },
                onClear = {
                    viewModel.confirmCountClear()
                }
            )
        }
    }
    ConfirmDialog(
        title = "Are you sure you want to clear the counter?",
        show = uiEvent.value == CounterViewModel.UiEvent.ConfirmCountClear,
        onClickOk = {
            viewModel.clearCount()
        },
        onClickCancel = {
            viewModel.setIdleState()
        }
    )
}

@Composable
private fun CounterViewLayout(
    count: Int,
    onIncrement: () -> Unit = {},
    onClear: () -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Count: $count")

        Button(onClick = onIncrement) {
            Text(text = "Increment")
        }

        Button(onClick = onClear) {
            Text(text = "Clear")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DangerandroidexampleTheme {
        CounterViewLayout(count = 12345)
    }
}