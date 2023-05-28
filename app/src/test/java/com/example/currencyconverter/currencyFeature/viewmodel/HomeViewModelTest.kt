package com.example.currencyconverter.currencyFeature.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.runtime.collectAsState
import com.example.currencyconverter.common.Constants
import com.example.currencyconverter.currencyFeature.model.LatestModel
import com.example.currencyconverter.currencyFeature.view.HomeViewModel
import com.example.currencyconverter.utils.ViewState
import com.example.currencyconverter.utils.ViewStateForConversion
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

    private lateinit var sut: HomeViewModel
    private var fakeAmount: Double? = null
    private var fakeDividerAmount: Double? = null
    private var fakeBaseAmount: Double? = null
    private var fakeBase: String? = null

    @get:Rule
    val initExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        sut = HomeViewModel(
            FakeLatestUseCase(),
            FakeCurrencyConversionUseCase()
        )
        fakeAmount = 3.0
        fakeBaseAmount = 3.32
        fakeDividerAmount = 3.32
        fakeBase = "USD"
    }

    @Test
    fun `call getCurrencies() method`() {

        sut.getCurrencies(Constants.APP_ID)
        Truth.assertThat((sut.viewState.value))
            .isEqualTo(
                ViewState.Success(
                    LatestModel(
                        base = "HUN",
                        disclaimer = null,
                        license = null,
                        timestamp = -1
                    )
                )
            )
    }

    @Test
    fun `conversion initial state is Loading`() {
        Truth.assertThat((sut.viewStateForConversion.value)).isEqualTo(
            ViewStateForConversion.Loading
        )
    }

    @Test
    fun `conversion usecase state is success`() = runTest {

        sut.conversionUseCaseMethod(
            fakeAmount!!,
            fakeDividerAmount!!,
            fakeBaseAmount!!,
            fakeBase!!
        )
        Truth.assertThat(
            (sut.viewStateForConversion.value)
        ).isEqualTo(
            ViewStateForConversion.Success(
                mapOf(
                    "USD" to "3.0"
                )
            )
        )

    }

    @Test
    fun `conversion usecase state is error`() = runTest {

        sut.conversionUseCaseMethod(
            fakeAmount!!,
            fakeDividerAmount!!,
            fakeBaseAmount!!,
            fakeBase!!
        )
        Truth.assertThat(
            (sut.viewStateForConversion.value)
        ).isNotEqualTo(
            ViewStateForConversion.Success(
                mapOf(
                    "USD" to "3"
                )
            )
        )

    }

    @Test
    fun `check amount state initial value is empty`() {
        Truth.assertThat(sut.amountState.value?.isNotEmpty()).isFalse()
    }

    @Test
    fun `check amount state changes when text field value changed`() {
        sut.setAmount("2")
        Truth.assertThat(sut.amountState.value == "2")
    }

    @Test
    fun `check currency state initial value is empty`() {
        Truth.assertThat(sut.currencyState.value?.isNotEmpty()).isFalse()
    }

    @Test
    fun `check currency state changes when text field value changed`() {
        sut.setCurrency("USD")
        Truth.assertThat(sut.currencyState.value == "USD")
    }


}