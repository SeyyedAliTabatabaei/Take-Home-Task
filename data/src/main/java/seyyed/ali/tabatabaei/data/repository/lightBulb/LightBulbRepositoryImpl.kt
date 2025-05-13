package seyyed.ali.tabatabaei.data.repository.lightBulb

import kotlinx.coroutines.flow.Flow
import seyyed.ali.tabatabaei.data.Constants
import seyyed.ali.tabatabaei.domain.model.lightStatus.LightBulbStatus
import seyyed.ali.tabatabaei.domain.repositories.lightBulb.LightBulbRemoteDataSource
import seyyed.ali.tabatabaei.domain.repositories.lightBulb.LightBulbRepository
import seyyed.ali.tabatabaei.domain.repositories.mqtt.MqttRemoteDataSource

class LightBulbRepositoryImpl(
    private val lightBulbRemoteDataSource: LightBulbRemoteDataSource
) : LightBulbRepository {

    override fun setLightBulbStatus(requestData: LightBulbStatus.Request) {
        lightBulbRemoteDataSource.setLightBulbStatus(requestData)
    }

    override val observeLightBulbStatus: Flow<LightBulbStatus.Response>
        get() = lightBulbRemoteDataSource.observeLightBulbStatus

    override fun clearLightBulbStatus() {
        lightBulbRemoteDataSource.clearLightBulbStatus()
    }


}