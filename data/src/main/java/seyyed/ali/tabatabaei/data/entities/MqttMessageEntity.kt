package seyyed.ali.tabatabaei.data.entities

import org.eclipse.paho.client.mqttv3.MqttMessage

data class MqttMessageEntity(
    val topic : String ,
    val message : String?
)