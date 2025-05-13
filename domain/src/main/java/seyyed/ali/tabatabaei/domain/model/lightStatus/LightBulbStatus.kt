package seyyed.ali.tabatabaei.domain.model.lightStatus

import seyyed.ali.tabatabaei.domain.model.enums.BulbStatus

sealed class LightBulbStatus {

    data class Request(
        val status: BulbStatus
    ) : LightBulbStatus()

    data class Response(
        val lightBulbStatus: BulbStatus
    ) : LightBulbStatus()

}
