package seyyed.ali.tabatabaei.take_home.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import seyyed.ali.tabatabaei.data.repository.lightBulb.LightBulbRepositoryImpl
import seyyed.ali.tabatabaei.data.repository.mqtt.MqttRepositoryImpl
import seyyed.ali.tabatabaei.domain.repositories.lightBulb.LightBulbRemoteDataSource
import seyyed.ali.tabatabaei.domain.repositories.lightBulb.LightBulbRepository
import seyyed.ali.tabatabaei.domain.repositories.mqtt.MqttRemoteDataSource
import seyyed.ali.tabatabaei.domain.repositories.mqtt.MqttRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModules {

    @Singleton
    @Provides
    fun provideMqttRepository(mqttRemoteDataSource: MqttRemoteDataSource) : MqttRepository {
        return MqttRepositoryImpl(mqttRemoteDataSource)
    }

    @Singleton
    @Provides
    fun provideLightBulbRepository(lightBulbRemoteDataSource: LightBulbRemoteDataSource) : LightBulbRepository {
        return LightBulbRepositoryImpl(lightBulbRemoteDataSource)
    }
}