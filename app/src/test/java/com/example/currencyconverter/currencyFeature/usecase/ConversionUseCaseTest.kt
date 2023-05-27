package com.example.currencyconverter.currencyFeature.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ConversionUseCaseTest {


    @get:Rule
    val initExecutorRule = InstantTaskExecutorRule() //needed for background executor management


    private val testDispatcher = StandardTestDispatcher()
    val fakeAmount = 3.0;
    val fakeDividerAmount = 3.3;
    val fakeBaseAmount = 3.3
    val base = "USD"

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

    }


    @Test
    fun `returns not empty map`() = runTest {
        val sut = ConversionUseCase()
        val result = sut.invoke(
            fakeAmount,
            fakeDividerAmount,
            fakeBaseAmount,
            base
        )
        Truth.assertThat(result.isEmpty()).isFalse()
    }

    @Test
    fun `returns map of base and multiplied amount`() = runTest {
        val sut = ConversionUseCase()
        val result = sut.invoke(
            fakeAmount,
            fakeDividerAmount,
            fakeBaseAmount,
            base
        )
        Truth.assertThat(result["USD"] == "3.0").isTrue()
    }


    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}