package seyyed.ali.tabatabaei.domain.useCase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import seyyed.ali.tabatabaei.domain.model.base.ResultEntity
import seyyed.ali.tabatabaei.domain.repositories.lightBulb.LightBulbRepository

class ClearLightBulbStatusUseCase(
    private val lightBulbRepository: LightBulbRepository ,
    private val coroutineDispatcher: CoroutineDispatcher
) : BaseUseCase<Unit , ResultEntity<Unit>>(coroutineDispatcher) {

    override fun execute(parameters: Unit): Flow<ResultEntity<Unit>> = flow {
        try {
            lightBulbRepository.clearLightBulbStatus()
            emit(ResultEntity.Success(Unit))
        } catch (e: Exception) {
            emit(ResultEntity.Error(e.message ?: "Know Error"))
        }
    }

}