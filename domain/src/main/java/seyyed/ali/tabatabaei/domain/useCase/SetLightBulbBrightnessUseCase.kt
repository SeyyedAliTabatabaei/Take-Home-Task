package seyyed.ali.tabatabaei.domain.useCase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import seyyed.ali.tabatabaei.domain.model.base.ResultEntity
import seyyed.ali.tabatabaei.domain.model.lightStatus.LightBulbBrightness
import seyyed.ali.tabatabaei.domain.model.lightStatus.LightBulbStatus
import seyyed.ali.tabatabaei.domain.repositories.lightBulb.LightBulbRepository

class SetLightBulbBrightnessUseCase(
    private val lightBulbRepository: LightBulbRepository ,
    private val coroutineDispatcher: CoroutineDispatcher
) : BaseUseCase<LightBulbBrightness.Request , ResultEntity<Unit>>(coroutineDispatcher) {

    override fun execute(parameters: LightBulbBrightness.Request): Flow<ResultEntity<Unit>> = flow {
        try {
            lightBulbRepository.setLightBulbBrightness(parameters)
            emit(ResultEntity.Success(Unit))
        } catch (e: Exception) {
            emit(ResultEntity.Error(e.message ?: "Know Error"))
        }
    }

}