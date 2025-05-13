package seyyed.ali.tabatabaei.take_home.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.stateIn
import seyyed.ali.tabatabaei.domain.model.enums.BulbStatus
import seyyed.ali.tabatabaei.domain.model.enums.MqttConnectionStatus
import seyyed.ali.tabatabaei.domain.model.lightStatus.LightBulbStatus
import seyyed.ali.tabatabaei.domain.useCase.LightBulbStatusUseCase
import seyyed.ali.tabatabaei.domain.useCase.MqttConnectionStatusUseCase
import seyyed.ali.tabatabaei.domain.useCase.SetLightBulbStatusUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mqttConnectionStatusUseCase: MqttConnectionStatusUseCase ,
    private val lightBulbStatusUseCase: LightBulbStatusUseCase ,
    private val setLightBulbStatusUseCase: SetLightBulbStatusUseCase
) : ViewModel() {

    val connectionStatus = mqttConnectionStatusUseCase.invoke(Unit)
        .stateIn(
            viewModelScope , SharingStarted.Eagerly , MqttConnectionStatus.DISCONNECTED
        )

    val lightBulbStatus = lightBulbStatusUseCase.invoke(Unit)
        .stateIn(
            viewModelScope , SharingStarted.Eagerly , LightBulbStatus.Response(BulbStatus.OFF)
        )

    fun setLightStatus() {
        setLightBulbStatusUseCase(LightBulbStatus.Request(
            when(lightBulbStatus.value.lightBulbStatus){
                BulbStatus.ON -> BulbStatus.OFF
                BulbStatus.OFF -> BulbStatus.ON
            }
        )).launchIn(viewModelScope)
    }
}