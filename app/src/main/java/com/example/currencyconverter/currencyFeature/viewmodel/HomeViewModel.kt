package com.example.currencyconverter.currencyFeature.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.currencyFeature.model.LatestModel
import com.example.currencyconverter.currencyFeature.usecase.IConversionUseCase
import com.example.currencyconverter.currencyFeature.usecase.ILatestUseCase
import com.example.currencyconverter.utils.ViewState
import com.example.currencyconverter.utils.ViewStateForConversion
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    val useCase: ILatestUseCase,
    val conversionUseCase: IConversionUseCase
) : ViewModel() {


    private val _viewState: MutableLiveData<ViewState> =
        MutableLiveData(ViewState.Loading)

    val viewState: LiveData<ViewState> = _viewState


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


    private val _viewSateForConversion: MutableLiveData<ViewStateForConversion> =
        MutableLiveData(ViewStateForConversion.Loading)

    val viewStateForConversion: LiveData<ViewStateForConversion> =
        _viewSateForConversion


    fun conversionUseCaseMethod(
        amount: Double,
        dividerAmount: Double,
        baseAmount: Double,
        base: String
    ) {
        viewModelScope.launch {

            try {
                var resultMap: Map<String, String> = conversionUseCase(
                    amount,
                    dividerAmount,
                    baseAmount,
                    base
                )
                _viewSateForConversion.value = ViewStateForConversion.Success(resultMap)
            } catch (e: Exception) {
                _viewSateForConversion.value =
                    ViewStateForConversion.Error(e.message ?: "Unknown Error Occured")
            }
        }
    }


}