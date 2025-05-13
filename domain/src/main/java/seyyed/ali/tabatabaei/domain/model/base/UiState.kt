package seyyed.ali.tabatabaei.domain.model.base

sealed class UiState<out T> {
    data object Idle : UiState<Nothing>()
    data object Loading : UiState<Nothing>()
    data class Error(val message: String ?= null) : UiState<Nothing>()
    data class Success<out T>(val data: T) : UiState<T>()
}