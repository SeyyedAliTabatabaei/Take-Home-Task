package seyyed.ali.tabatabaei.take_home.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import seyyed.ali.tabatabaei.data.config.mqtt.MqttManager
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModules {

    @Singleton
    @Provides
    fun provideMqttManager(@ApplicationContext context: Context) : MqttManager {
        return MqttManager(context)
    }

}