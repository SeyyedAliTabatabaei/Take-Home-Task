package seyyed.ali.tabatabaei.data.datasource

import kotlinx.coroutines.flow.Flow
import seyyed.ali.tabatabaei.data.config.mqtt.MqttManager
import seyyed.ali.tabatabaei.domain.model.enums.MqttConnectionStatus
import seyyed.ali.tabatabaei.domain.repositories.mqtt.MqttRemoteDataSource
import seyyed.ali.tabatabaei.domain.repositories.mqtt.MqttRepository

class MqttRemoteDataSourceImpl(
    private val mqttManager: MqttManager
) : MqttRemoteDataSource {

    override fun subscribe(topic: String) {
        mqttManager.subscribe(topic)
    }

    override fun unsubscribe(topic: String) {
        mqttManager.unsubscribe(topic)
    }

    override fun publish(topic: String, msg: String, qos: Int, retained: Boolean) {
        mqttManager.publish(topic, msg, qos, retained)
    }

    override fun disconnect() {
        mqttManager.disconnect()
    }

    override val observeConnectionStatus: Flow<MqttConnectionStatus>
        get() = mqttManager.connectionStatus

}