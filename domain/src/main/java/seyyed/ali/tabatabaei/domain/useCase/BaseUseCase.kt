package seyyed.ali.tabatabaei.domain.useCase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

abstract class BaseUseCase<in P , R>(private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO) {
    operator fun invoke(parameters : P) : Flow<R> {
        return execute(parameters)
            .flowOn(coroutineDispatcher)
    }

    abstract fun execute(parameters: P) : Flow<R>
}