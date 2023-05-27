package com.example.currencyconverter.currencyFeature.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
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

    private lateinit var homeViewModel: HomeViewModel
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
        homeViewModel = HomeViewModel(
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

        homeViewModel.getCurrencies(Constants.APP_ID)
        Truth.assertThat((homeViewModel.viewState.value))
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
        Truth.assertThat((homeViewModel.viewStateForConversion.value)).isEqualTo(
            ViewStateForConversion.Loading
        )
    }

    @Test
    fun `conversion usecase state is success`() = runTest {

        homeViewModel.conversionUseCaseMethod(
            fakeAmount!!,
            fakeDividerAmount!!,
            fakeBaseAmount!!,
            fakeBase!!
        )
        Truth.assertThat(
            (homeViewModel.viewStateForConversion.value)
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

        homeViewModel.conversionUseCaseMethod(
            fakeAmount!!,
            fakeDividerAmount!!,
            fakeBaseAmount!!,
            fakeBase!!
        )
        Truth.assertThat(
            (homeViewModel.viewStateForConversion.value)
        ).isNotEqualTo(
            ViewStateForConversion.Success(
                mapOf(
                    "USD" to "3"
                )
            )
        )

    }


}