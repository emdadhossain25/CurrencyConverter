package com.example.currencyconverter.currencies.currencyfeature

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.currencies.model.LatestModel
import com.example.currencyconverter.currencies.domain.ILatestUseCase
import com.example.currencyconverter.db.CurrencyConverterDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


sealed class ViewState {
    object Loading : ViewState()
    data class Success(val data: LatestModel) : ViewState()
    data class Error(val errorMessage: String) : ViewState()
}


@HiltViewModel
class HomeViewModel @Inject constructor(
    val useCase: ILatestUseCase,
    val currencyConverterDao: CurrencyConverterDao
) : ViewModel() {

    private val _viewState: MutableState<ViewState> =
        mutableStateOf(ViewState.Loading)

    val viewState: State<ViewState> = _viewState
    fun getCurrencies(app_id: String) {

        viewModelScope.launch {
            try {

                var latestModel: LatestModel = useCase(app_id)
                _viewState.value = ViewState.Success(latestModel)
            } catch (e: Exception) {
                _viewState.value = ViewState.Error(e.message ?: "Unknown Error")
            }
        }
    }


}