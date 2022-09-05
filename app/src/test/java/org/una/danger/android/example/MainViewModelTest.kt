package org.una.danger.android.example

import androidx.compose.runtime.snapshotFlow
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.una.danger.android.example.usecase.CounterUseCaseMock
import org.una.danger.android.example.view.CounterViewModel

class MainViewModelTest {
    @OptIn(DelicateCoroutinesApi::class)
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun test_incrementCount() = runTest {
        // Given
        val expected = 100
        var uiState: CounterViewModel.UiState? = null
        val useCaseMock = CounterUseCaseMock(
            incrementStub = { expected }
        )
        val viewModel = CounterViewModel(useCaseMock)
        val job = launch {
            uiState = snapshotFlow { viewModel.uiState }
                .take(1)
                .first()
                .value
        }

        // When
        viewModel.incrementCount()
        job.join()

        // Then
        assert(expected == uiState?.count)
        assert(1 == useCaseMock.incrementSpy)
    }

    @Test
    fun test_clearCount() = runTest {
        // Given
        val expected = 0
        var uiState: CounterViewModel.UiState? = null
        val useCaseMock = CounterUseCaseMock(
            clearStub = { expected }
        )
        val viewModel = CounterViewModel(useCaseMock)
        val job = launch {
            uiState = snapshotFlow { viewModel.uiState }
                .take(1)
                .first()
                .value
        }

        // When
        viewModel.clearCount()
        job.join()

        // Then
        assert(expected == uiState?.count)
        assert(1 == useCaseMock.clearSpy)
    }
}