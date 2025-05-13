package seyyed.ali.tabatabaei.domain.useCase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import seyyed.ali.tabatabaei.domain.model.lightStatus.LightBulbBrightness
import seyyed.ali.tabatabaei.domain.repositories.lightBulb.LightBulbRepository

class LightBulbBrightnessUseCase(
    private val lightBulbRepository: LightBulbRepository ,
    private val coroutineDispatcher: CoroutineDispatcher
) : BaseUseCase<Unit , LightBulbBrightness.Response>(coroutineDispatcher) {

    override fun execute(parameters: Unit): Flow<LightBulbBrightness.Response> {
        return lightBulbRepository.observeLightBulbBrightness
    }

}