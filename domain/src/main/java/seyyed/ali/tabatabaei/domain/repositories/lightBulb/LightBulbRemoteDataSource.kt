package seyyed.ali.tabatabaei.domain.repositories.lightBulb

import kotlinx.coroutines.flow.Flow
import seyyed.ali.tabatabaei.domain.model.lightStatus.LightBulbBrightness
import seyyed.ali.tabatabaei.domain.model.lightStatus.LightBulbStatus

interface LightBulbRemoteDataSource {
    fun setLightBulbStatus(requestData: LightBulbStatus.Request)

    val observeLightBulbStatus : Flow<LightBulbStatus.Response>

    fun setLightBulbBrightness(requestData: LightBulbBrightness.Request)

    val observeLightBulbBrightness : Flow<LightBulbBrightness.Response>

    fun clearLightBulbStatus()
}