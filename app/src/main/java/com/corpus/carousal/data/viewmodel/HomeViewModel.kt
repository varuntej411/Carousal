package com.corpus.carousal.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.corpus.carousal.domain.GetHomeDataUseCase
import com.corpus.carousal.domain.model.CarousalResponse
import com.corpus.carousal.utils.APIDataStatus
import com.corpus.carousal.utils.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getHomeDataUseCase: GetHomeDataUseCase) :
    ViewModel() {

    // LiveData to hold the list of data
    private val _uiState = MutableStateFlow(DataList.UiState())
    val uiState: StateFlow<DataList.UiState> = _uiState.asStateFlow()

    init {
        fetchDataFromJson()
    }

    private fun fetchDataFromJson() = getHomeDataUseCase.invoke().onEach { result ->
        when (result) {
            is APIDataStatus.LOADING -> {
                _uiState.update {
                    DataList.UiState(isLoading = true)
                }
            }

            is APIDataStatus.SUCCESS -> {
                _uiState.update {
                    DataList.UiState(isLoading = false, data = result.data)
                }
            }

            is APIDataStatus.ERROR -> {
                _uiState.update {
                    DataList.UiState(
                        isLoading = false,
                        error = UiText.RemoteString(
                            result.message ?: "An Unexpected Error Occurred"
                        )
                    )
                }
            }
        }
    }.launchIn(viewModelScope)

}

object DataList {
    data class UiState(
        val isLoading: Boolean = false,
        val error: UiText = UiText.Idle,
        val data: CarousalResponse? = null,
    )
}
