package seyyed.ali.tabatabaei.take_home.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import seyyed.ali.tabatabaei.domain.model.enums.MqttConnectionStatus
import seyyed.ali.tabatabaei.domain.repositories.lightBulb.LightBulbRepository
import seyyed.ali.tabatabaei.domain.repositories.mqtt.MqttRepository
import seyyed.ali.tabatabaei.domain.useCase.ClearLightBulbStatusUseCase
import seyyed.ali.tabatabaei.domain.useCase.LightBulbStatusUseCase
import seyyed.ali.tabatabaei.domain.useCase.MqttConnectionStatusUseCase
import seyyed.ali.tabatabaei.domain.useCase.SetLightBulbStatusUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModules {

    @Singleton
    @Provides
    fun provideMqttConnectionStatus(mqttRepository: MqttRepository) : MqttConnectionStatusUseCase{
        return MqttConnectionStatusUseCase(mqttRepository , Dispatchers.IO)
    }

    @Singleton
    @Provides
    fun provideSetLightBulbStatusUseCase(lightBulbRepository: LightBulbRepository) : SetLightBulbStatusUseCase{
        return SetLightBulbStatusUseCase(lightBulbRepository , Dispatchers.IO)
    }

    @Singleton
    @Provides
    fun provideClearLightBulbStatusUseCase(lightBulbRepository: LightBulbRepository) : ClearLightBulbStatusUseCase{
        return ClearLightBulbStatusUseCase(lightBulbRepository , Dispatchers.IO)
    }

    @Singleton
    @Provides
    fun provideLightBulbStatusUseCasee(lightBulbRepository: LightBulbRepository) : LightBulbStatusUseCase{
        return LightBulbStatusUseCase(lightBulbRepository , Dispatchers.IO)
    }


}