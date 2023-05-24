package com.example.currencyconverter.currencyFeature.usecase

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.currencyconverter.common.Constants
import com.example.currencyconverter.currencyFeature.repository.LatestRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import javax.inject.Inject


@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
@HiltAndroidTest
class LatestUseCaseTest {

    @get:Rule
    val initExecutorRule = InstantTaskExecutorRule() //needed for background executor management

    @get:Rule
    val hiltAndroidRule = HiltAndroidRule(this) // needed for injecting

    @Inject
    lateinit var latestRepository: LatestRepository

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        hiltAndroidRule.inject()
        Dispatchers.setMain(testDispatcher)
    }


    @Test
    fun callRepository_ReturnsLatestModel_CorrectTest() = runTest {
        val sut = LatestUseCase(latestRepository)
        val result = sut.invoke(Constants.APP_ID)
        Assert.assertTrue("385.886279" == result.rates?.get("AMD"))
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}