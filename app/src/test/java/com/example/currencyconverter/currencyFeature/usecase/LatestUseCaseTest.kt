package com.example.currencyconverter.currencyFeature.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.currencyconverter.common.Constants
import com.example.currencyconverter.currencyFeature.repository.FakeLatestRepository
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
import javax.inject.Inject

class LatestUseCaseTest {


    @get:Rule
    val initExecutorRule = InstantTaskExecutorRule() //needed for background executor management

    @Inject
    lateinit var fakeLatestRepository: FakeLatestRepository

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        fakeLatestRepository = FakeLatestRepository()
        Dispatchers.setMain(testDispatcher)

    }


    @Test
    fun callRepository_ReturnsLatestModel_CorrectTest() = runTest {
        val sut = LatestUseCase(fakeLatestRepository)
        val result = sut.invoke(Constants.APP_ID)
        var currency = result.rates?.get("LFD") ?: "USD"

        Truth.assertThat(currency == "121").isFalse()

    }

    @Test
    fun callRepository_ReturnsLatestModel_InCorrectTest() = runTest {
        val sut = LatestUseCase(fakeLatestRepository)
        val result = sut.invoke(Constants.APP_ID)
        var value = result.rates?.get("LFD")
        Truth.assertThat("385.94" == value).isTrue()
    }

    @Test
    fun `test default value for timestamp is equal -1`() = runTest {
        val sut = LatestUseCase(fakeLatestRepository)
        val result = sut.invoke(Constants.APP_ID)

        Truth.assertThat(-1 == result.timestamp).isTrue()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}