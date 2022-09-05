package org.una.danger.android.example

import org.junit.Before
import org.junit.Test
import org.una.danger.android.example.usecase.CounterUseCaseMock
import org.una.danger.android.example.view.CounterViewModel

class MainViewModelTest {
    private lateinit var viewModel: CounterViewModel

    @Before
    fun setUp() {
        viewModel = CounterViewModel(CounterUseCaseMock())
    }

    @Test
    fun test_incrementCount() {
        //viewModel.incrementCount()
    }
}