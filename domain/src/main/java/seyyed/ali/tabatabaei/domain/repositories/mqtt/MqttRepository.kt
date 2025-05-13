package seyyed.ali.tabatabaei.domain.repositories.mqtt

import kotlinx.coroutines.flow.Flow
import seyyed.ali.tabatabaei.domain.model.enums.MqttConnectionStatus

interface MqttRepository {

    fun subscribe(topic: String)

    fun unsubscribe(topic: String)

    fun publish(topic: String, msg: String, qos: Int = 1, retained: Boolean = false)

    fun disconnect()

    val observeConnectionStatus : Flow<MqttConnectionStatus>

}