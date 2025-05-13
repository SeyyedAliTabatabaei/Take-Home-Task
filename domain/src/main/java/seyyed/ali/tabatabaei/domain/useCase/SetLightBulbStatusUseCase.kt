package seyyed.ali.tabatabaei.domain.useCase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import seyyed.ali.tabatabaei.domain.model.base.ResultEntity
import seyyed.ali.tabatabaei.domain.model.lightStatus.LightBulbStatus
import seyyed.ali.tabatabaei.domain.repositories.lightBulb.LightBulbRepository

class SetLightBulbStatusUseCase(
    private val lightBulbRepository: LightBulbRepository ,
    private val coroutineDispatcher: CoroutineDispatcher
) : BaseUseCase<LightBulbStatus.Request , ResultEntity<Unit>>(coroutineDispatcher) {

    override fun execute(parameters: LightBulbStatus.Request): Flow<ResultEntity<Unit>> = flow {
        try {
            lightBulbRepository.setLightBulbStatus(parameters)
            emit(ResultEntity.Success(Unit))
        } catch (e: Exception) {
            emit(ResultEntity.Error(e.message ?: "Know Error"))
        }
    }

}