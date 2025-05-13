package seyyed.ali.tabatabaei.take_home.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import seyyed.ali.tabatabaei.data.config.mqtt.MqttManager
import seyyed.ali.tabatabaei.data.datasource.LightBulbRemoteDataSourceImpl
import seyyed.ali.tabatabaei.data.datasource.MqttRemoteDataSourceImpl
import seyyed.ali.tabatabaei.domain.repositories.lightBulb.LightBulbRemoteDataSource
import seyyed.ali.tabatabaei.domain.repositories.mqtt.MqttRemoteDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModules {

    @Singleton
    @Provides
    fun provideMqttRemoteDataSource(mqttManager: MqttManager) : MqttRemoteDataSource{
        return MqttRemoteDataSourceImpl(mqttManager)
    }

    @Singleton
    @Provides
    fun provideLightBulbRemoteDataSource(mqttManager: MqttManager) : LightBulbRemoteDataSource{
        return LightBulbRemoteDataSourceImpl(mqttManager)
    }
}