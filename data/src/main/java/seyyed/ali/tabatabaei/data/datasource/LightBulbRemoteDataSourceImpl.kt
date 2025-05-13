package seyyed.ali.tabatabaei.data.datasource

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import seyyed.ali.tabatabaei.data.Constants
import seyyed.ali.tabatabaei.data.config.mqtt.MqttManager
import seyyed.ali.tabatabaei.domain.model.enums.BulbStatus
import seyyed.ali.tabatabaei.domain.model.enums.MqttConnectionStatus
import seyyed.ali.tabatabaei.domain.model.lightStatus.LightBulbStatus
import seyyed.ali.tabatabaei.domain.repositories.lightBulb.LightBulbRemoteDataSource
import seyyed.ali.tabatabaei.domain.repositories.mqtt.MqttRemoteDataSource
import seyyed.ali.tabatabaei.domain.repositories.mqtt.MqttRepository

class LightBulbRemoteDataSourceImpl(
    private val mqttManager: MqttManager
) : LightBulbRemoteDataSource {

    override fun setLightBulbStatus(requestData: LightBulbStatus.Request) {
        mqttManager.publish(Constants.LIGHT_BULB_STATUS_TOPIC , requestData.status.name)
    }

    override val observeLightBulbStatus: Flow<LightBulbStatus.Response>
        get() {
            mqttManager.subscribe(Constants.LIGHT_BULB_STATUS_TOPIC)
            return mqttManager.messages.filter { it.topic == Constants.LIGHT_BULB_STATUS_TOPIC }.map {
                return@map LightBulbStatus.Response(
                    lightBulbStatus = when(it.message) {
                        "ON" -> BulbStatus.ON
                        "OFF" -> BulbStatus.OFF
                        else -> BulbStatus.OFF
                    }
                )
            }
        }

    override fun clearLightBulbStatus() {
        mqttManager.unsubscribe(Constants.LIGHT_BULB_STATUS_TOPIC)
    }


}