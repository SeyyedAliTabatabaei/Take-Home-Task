package seyyed.ali.tabatabaei.domain.useCase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import seyyed.ali.tabatabaei.domain.model.enums.MqttConnectionStatus
import seyyed.ali.tabatabaei.domain.repositories.mqtt.MqttRepository

class MqttConnectionStatusUseCase(
    private val mqttRepository: MqttRepository ,
    private val coroutineDispatcher: CoroutineDispatcher
) : BaseUseCase<Unit , MqttConnectionStatus>(coroutineDispatcher) {

    override fun execute(parameters: Unit): Flow<MqttConnectionStatus> {
        return mqttRepository.observeConnectionStatus
    }

}