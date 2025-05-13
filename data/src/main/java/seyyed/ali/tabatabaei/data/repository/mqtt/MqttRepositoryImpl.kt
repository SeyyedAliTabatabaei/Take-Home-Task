package seyyed.ali.tabatabaei.data.repository.mqtt

import kotlinx.coroutines.flow.Flow
import seyyed.ali.tabatabaei.domain.model.enums.MqttConnectionStatus
import seyyed.ali.tabatabaei.domain.repositories.mqtt.MqttRemoteDataSource
import seyyed.ali.tabatabaei.domain.repositories.mqtt.MqttRepository

class MqttRepositoryImpl(
    private val mqttRemoteDataSource: MqttRemoteDataSource
) : MqttRepository {

    override fun subscribe(topic: String) {
        mqttRemoteDataSource.subscribe(topic)
    }

    override fun unsubscribe(topic: String) {
        mqttRemoteDataSource.unsubscribe(topic)
    }

    override fun publish(topic: String, msg: String, qos: Int, retained: Boolean) {
        mqttRemoteDataSource.publish(topic, msg, qos, retained)
    }

    override fun disconnect() {
        mqttRemoteDataSource.disconnect()
    }

    override val observeConnectionStatus: Flow<MqttConnectionStatus>
        get() = mqttRemoteDataSource.observeConnectionStatus
}