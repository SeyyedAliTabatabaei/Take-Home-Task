package seyyed.ali.tabatabaei.data.datasource

import seyyed.ali.tabatabaei.domain.model.base.ResultEntity

abstract class BaseDataSource {
    suspend fun <T> safe(query : suspend () -> T) : ResultEntity<T> {
        return try {
            query.invoke().let {
                ResultEntity.Success(it)
            }
        } catch (e : Exception) {
            ResultEntity.Error(e.message.toString())
        }
    }
}