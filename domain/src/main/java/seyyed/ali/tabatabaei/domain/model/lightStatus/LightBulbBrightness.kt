package seyyed.ali.tabatabaei.domain.model.lightStatus

import seyyed.ali.tabatabaei.domain.model.enums.BulbStatus

sealed class LightBulbBrightness {

    data class Request(
        val brightness: Int
    ) : LightBulbBrightness()

    data class Response(
        val brightness: Int
    ) : LightBulbBrightness()

}
