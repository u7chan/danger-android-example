package org.una.danger.android.example.usecase

interface CounterUseCase {
    fun increment(): Result<Int>
    fun clear(): Result<Int>
}

class CounterUseCaseMock : CounterUseCase {
    override fun increment(): Result<Int> = Result.success(1)
    override fun clear(): Result<Int> = Result.success(0)
}

class CounterUseCaseImpl : CounterUseCase {
    private var count: Int = 0
    override fun increment(): Result<Int> {
        count++
        return Result.success(count)
    }

    override fun clear(): Result<Int> {
        count = 0
        return Result.success(count)
    }
}