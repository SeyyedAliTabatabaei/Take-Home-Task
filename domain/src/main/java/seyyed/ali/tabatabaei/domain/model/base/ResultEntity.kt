package seyyed.ali.tabatabaei.domain.model.base

sealed class ResultEntity<out R> {
    data class Success<out T>(val data: T) : ResultEntity<T>()
    data class Error(var error: String) : ResultEntity<Nothing>()
}