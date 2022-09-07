package org.una.danger.android.example.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.una.danger.android.example.usecase.CounterUseCase

class CounterViewModel(private val counterUseCase: CounterUseCase) : ViewModel() {

    data class UiState(
        val count: Int = 0
    ) {
        companion object {
            val default = UiState()
        }
    }

    sealed interface UiEvent {
        object Idle : UiEvent
        object ConfirmCountClear : UiEvent
        /* data class Error(val e: Throwable) : UiEvent */
    }

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.default)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _uiEvent: Channel<UiEvent> = Channel()
    val uiEvent: Flow<UiEvent> = _uiEvent.receiveAsFlow()

    fun incrementCount() = viewModelScope.launch {
        counterUseCase.increment().onSuccess { newValue ->
            _uiState.update { state ->
                state.copy(count = newValue)
            }
        }
    }

    fun confirmCountClear() = viewModelScope.launch {
        _uiEvent.send(UiEvent.ConfirmCountClear)
    }

    fun clearCount() = viewModelScope.launch {
        counterUseCase.clear().onSuccess { newValue ->
            _uiState.update { state ->
                state.copy(count = newValue)
            }
            _uiEvent.send(UiEvent.Idle)
        }
    }

    fun setIdleState() = viewModelScope.launch {
        _uiEvent.send(UiEvent.Idle)
    }
}
