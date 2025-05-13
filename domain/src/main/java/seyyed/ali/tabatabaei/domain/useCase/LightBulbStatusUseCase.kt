package seyyed.ali.tabatabaei.domain.useCase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import seyyed.ali.tabatabaei.domain.model.enums.MqttConnectionStatus
import seyyed.ali.tabatabaei.domain.model.lightStatus.LightBulbStatus
import seyyed.ali.tabatabaei.domain.repositories.lightBulb.LightBulbRepository
import seyyed.ali.tabatabaei.domain.repositories.mqtt.MqttRepository

class LightBulbStatusUseCase(
    private val lightBulbRepository: LightBulbRepository ,
    private val coroutineDispatcher: CoroutineDispatcher
) : BaseUseCase<Unit , LightBulbStatus.Response>(coroutineDispatcher) {

    override fun execute(parameters: Unit): Flow<LightBulbStatus.Response> {
        return lightBulbRepository.observeLightBulbStatus
    }

}