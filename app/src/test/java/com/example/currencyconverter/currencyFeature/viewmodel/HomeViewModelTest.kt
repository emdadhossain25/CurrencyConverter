package com.example.currencyconverter.currencyFeature.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.currencyconverter.currencyFeature.repository.LatestRepository
import com.example.currencyconverter.currencyFeature.usecase.LatestUseCase
import com.example.currencyconverter.currencyFeature.view.HomeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations


class HomeViewModelTest {

    private lateinit var homeViewModel: HomeViewModel

    private val testDispatcher = StandardTestDispatcher()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var latestUseCase: LatestUseCase


    @Mock
    private lateinit var latestRepository: LatestRepository


    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        latestUseCase = LatestUseCase(
            latestRepository
        )
    }

    @Test
    fun checkTrue() = runTest {
        assert(1==1)
    }
    @Test
    fun checkNotTrue() = runTest {
        assert(1!=2)
    }


    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

}