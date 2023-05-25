package com.example.currencyconverter.currencyFeature.viewmodel

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.Observer
import com.example.currencyconverter.common.Constants
import com.example.currencyconverter.currencyFeature.model.LatestModel
import com.example.currencyconverter.currencyFeature.view.HomeViewModel
import com.example.currencyconverter.utils.ViewState
import com.google.common.truth.Truth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

    private lateinit var homeViewModel: HomeViewModel

    @get:Rule
    val initExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        homeViewModel = HomeViewModel(FakeLatestUseCase())
    }

    @Test
    fun `call getCurrencies() method`() {

        homeViewModel.getCurrencies(Constants.APP_ID)
        Truth.assertThat(homeViewModel.viewState.value).isEqualTo(ViewState.Success(LatestModel("HUN")))
    }

    @After
    fun tearDown() {
    }
}