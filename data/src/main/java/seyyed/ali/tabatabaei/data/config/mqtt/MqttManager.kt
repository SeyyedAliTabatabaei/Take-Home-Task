package seyyed.ali.tabatabaei.data.config.mqtt

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.util.Log
import cloud.deepblue.mqttfix.mqtt.MqttAndroidClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.lastOrNull
import kotlinx.coroutines.launch
import org.eclipse.paho.client.mqttv3.IMqttActionListener
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken
import org.eclipse.paho.client.mqttv3.IMqttToken
import org.eclipse.paho.client.mqttv3.MqttCallback
import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.eclipse.paho.client.mqttv3.MqttMessage
import seyyed.ali.tabatabaei.data.Constants
import seyyed.ali.tabatabaei.data.entities.MqttMessageEntity
import seyyed.ali.tabatabaei.data.utils.MonitorNetworkConnectivity
import seyyed.ali.tabatabaei.domain.model.enums.MqttConnectionStatus

class MqttManager(
    private val context: Context
) : ConnectivityManager.NetworkCallback() {

    private val TAG = "MqttManager"
    private val clientId = MqttClient.generateClientId()
    private val mqttClient = MqttAndroidClient(context , Constants.BASE_URL , clientId)
    private val mqttOptions = MqttConnectOptions().apply {
        isAutomaticReconnect = true
        isCleanSession = false
        keepAliveInterval = 30
    }

    private val mqttActionListener = object : IMqttActionListener{
        override fun onSuccess(asyncActionToken: IMqttToken?) {
            Log.i(TAG, "onSuccess: $asyncActionToken")
        }

        override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
            Log.i(TAG, "onFailure: $asyncActionToken -- ${exception?.message}")
        }
    }

    private val mqttListener = object : IMqttActionListener{
        override fun onSuccess(asyncActionToken: IMqttToken?) {
            Log.i(TAG, "onSuccess: $asyncActionToken")
            scope.launch {
                _connectionStatus.emit(MqttConnectionStatus.CONNECTED)
                subscribe(Constants.PING_TOPIC)
                keepConnectionMqtt()
            }
        }

        override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
            Log.i(TAG, "onFailure: $asyncActionToken --- ${exception?.message}")
            scope.launch {
                _connectionStatus.emit(MqttConnectionStatus.DISCONNECTED)
            }
        }
    }

    private val mqttCallback = object : MqttCallback{
        override fun connectionLost(cause: Throwable?) {
            Log.i(TAG, "connectionLost: ${cause?.message}")
            scope.launch {
                _connectionStatus.emit(MqttConnectionStatus.DISCONNECTED)
            }
        }

        override fun messageArrived(topic: String?, message: MqttMessage?) {
            Log.i(TAG, "messageArrived: $topic --- $message")
            topic?.let { t ->
                message?.toString()?.let { msg ->
                    scope.launch {
                        _messages.emit(
                            MqttMessageEntity(
                                topic = topic ,
                                message = message.payload?.toString(Charsets.UTF_8)
                            )
                        )
                    }
                }
            }
        }

        override fun deliveryComplete(token: IMqttDeliveryToken?) {
            Log.i(TAG, "deliveryComplete: $token")
        }
    }

    private val subscribeQueue = Channel<Pair<Boolean , String>>(capacity = Channel.UNLIMITED)

    private val _connectionStatus = MutableStateFlow(MqttConnectionStatus.DISCONNECTED)
    val connectionStatus : Flow<MqttConnectionStatus> = _connectionStatus.asStateFlow()

    private val _messages = MutableSharedFlow<MqttMessageEntity>()
    val messages: Flow<MqttMessageEntity> = _messages


    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private val connectionScope = CoroutineScope(Dispatchers.IO)
    private val keepConnectionScope = CoroutineScope(Dispatchers.IO)

    init {
        mqttClient.setCallback(mqttCallback)
        processQueueWhenConnected()
        MonitorNetworkConnectivity(context , this)
    }

    override fun onAvailable(network: Network) {
        super.onAvailable(network)
        connect()
    }

    private fun connect() {
        connectionScope.launch {
            if (mqttClient.isConnected) cancel()
            _connectionStatus.emit(MqttConnectionStatus.CONNECTING)
            mqttClient.connect(mqttOptions , mqttListener)
        }
    }

    fun disconnect() {
        mqttClient.disconnect()
    }

    fun subscribe(topic: String) {
        scope.launch {
            subscribeQueue.send(Pair(true , topic))
        }
    }

    fun unsubscribe(topic: String) {
        scope.launch {
            subscribeQueue.send(Pair(false , topic))
        }
    }

    fun publish(topic: String, msg: String, qos: Int = 1, retained: Boolean = false) {
        val message = MqttMessage()
        message.payload = msg.toByteArray()
        message.qos = qos
        message.isRetained = retained
        mqttClient.publish(topic, message, null, mqttActionListener)
    }


    private fun processQueueWhenConnected() {
        scope.launch {
            connectionStatus.filter { it == MqttConnectionStatus.CONNECTED }
                .collect {
                    for ((isSubscribe, topic) in subscribeQueue) {
                        Log.i(TAG, "processQueueWhenConnected: $isSubscribe -- $topic")
                        if (isSubscribe) {
                            mqttClient.subscribe(topic, 1 , null , mqttActionListener)
                        } else {
                            mqttClient.unsubscribe(topic , null , mqttActionListener)
                        }
                    }
                }
        }
    }

    private fun keepConnectionMqtt() {
        keepConnectionScope.cancel()
        keepConnectionScope.launch {
            while (true) {
                delay(30000)
                publish(Constants.PING_TOPIC , "ping")
            }
        }
    }


}