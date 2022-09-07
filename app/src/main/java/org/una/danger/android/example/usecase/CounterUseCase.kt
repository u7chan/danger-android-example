package org.una.danger.android.example.usecase

interface CounterUseCase {
    fun increment(): Result<Int>
    fun clear(): Result<Int>
}

class CounterUseCaseMock(
    val incrementStub: () -> Int = { -1 },
    val clearStub: () -> Int = { -1 }
) : CounterUseCase {
    var incrementSpy: Int = 0

    override fun increment(): Result<Int> {
        incrementSpy++
        return Result.success(incrementStub())
    }

    var clearSpy: Int = 0

    override fun clear(): Result<Int> {
        clearSpy++
        return Result.success(clearStub())
    }
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
