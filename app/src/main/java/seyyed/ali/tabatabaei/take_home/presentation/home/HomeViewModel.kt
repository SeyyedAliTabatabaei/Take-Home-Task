package seyyed.ali.tabatabaei.take_home.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.stateIn
import seyyed.ali.tabatabaei.domain.model.enums.BulbStatus
import seyyed.ali.tabatabaei.domain.model.enums.MqttConnectionStatus
import seyyed.ali.tabatabaei.domain.model.lightStatus.LightBulbBrightness
import seyyed.ali.tabatabaei.domain.model.lightStatus.LightBulbStatus
import seyyed.ali.tabatabaei.domain.useCase.ClearLightBulbStatusUseCase
import seyyed.ali.tabatabaei.domain.useCase.LightBulbBrightnessUseCase
import seyyed.ali.tabatabaei.domain.useCase.LightBulbStatusUseCase
import seyyed.ali.tabatabaei.domain.useCase.MqttConnectionStatusUseCase
import seyyed.ali.tabatabaei.domain.useCase.SetLightBulbBrightnessUseCase
import seyyed.ali.tabatabaei.domain.useCase.SetLightBulbStatusUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mqttConnectionStatusUseCase: MqttConnectionStatusUseCase ,
    private val lightBulbStatusUseCase: LightBulbStatusUseCase ,
    private val setLightBulbStatusUseCase: SetLightBulbStatusUseCase ,
    private val lightBulbBrightnessUseCase: LightBulbBrightnessUseCase ,
    private val setLightBulbBrightnessUseCase: SetLightBulbBrightnessUseCase ,
    private val clearLightBulbStatusUseCase: ClearLightBulbStatusUseCase
) : ViewModel() {

    val connectionStatus = mqttConnectionStatusUseCase.invoke(Unit)
        .stateIn(
            viewModelScope , SharingStarted.Eagerly , MqttConnectionStatus.DISCONNECTED
        )

    val lightBulbStatus = lightBulbStatusUseCase.invoke(Unit)
        .stateIn(
            viewModelScope , SharingStarted.Eagerly , LightBulbStatus.Response(BulbStatus.OFF)
        )

    val lightBulbBrightness = lightBulbBrightnessUseCase.invoke(Unit)
        .stateIn(
            viewModelScope , SharingStarted.Eagerly , LightBulbBrightness.Response(50)
        )

    fun toggleLightStatus() {
        setLightBulbStatusUseCase(LightBulbStatus.Request(
            when(lightBulbStatus.value.lightBulbStatus){
                BulbStatus.ON -> BulbStatus.OFF
                BulbStatus.OFF -> BulbStatus.ON
            }
        )).launchIn(viewModelScope)
    }

    fun setLightBrightness(brightness : Int) {
        setLightBulbBrightnessUseCase(LightBulbBrightness.Request(
            brightness
        )).launchIn(viewModelScope)
    }

    override fun onCleared() {
        super.onCleared()
        clearLightBulbStatusUseCase.invoke(Unit).launchIn(viewModelScope)
    }
}