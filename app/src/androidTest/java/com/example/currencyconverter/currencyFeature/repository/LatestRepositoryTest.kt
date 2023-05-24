package com.example.currencyconverter.currencyFeature.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.example.currencyconverter.common.Constants
import com.example.currencyconverter.currencyFeature.model.LatestModel
import com.example.currencyconverter.currencyFeature.service.IApiHelper
import com.example.currencyconverter.db.CurrencyConverterDao
import com.example.currencyconverter.db.CurrencyConverterDatabase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.mockito.MockitoAnnotations
import javax.inject.Inject


@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
@HiltAndroidTest
class LatestRepositoryTest {

    @get:Rule
    val initExecutorRule = InstantTaskExecutorRule() //needed for background executor management

    @get:Rule
    val hiltAndroidRule = HiltAndroidRule(this) // needed for injecting


    @Inject
    lateinit var currencyConverterDatabase: CurrencyConverterDatabase //injecting db using hilt


    @Inject
    lateinit var currencyConverterDao: CurrencyConverterDao

    @Inject
    lateinit var latestApi: IApiHelper


    private val testDispatcher = StandardTestDispatcher()

    private var latestModel: LatestModel? = LatestModel()

    @Before
    fun setup() {
        hiltAndroidRule.inject()
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        latestModel = LatestModel("LFD", null, null, null, 20)

    }


    @Test
    fun firstTestApiMockTest() = runTest {
        val item = latestApi.getLatestService(Constants.APP_ID).first()
        Assert.assertEquals("https://openexchangerates.org/license", item.body()?.license)


    }

    @Test
    fun testForIntegration_ReturnsDBOutPut() = runTest {


        val sut = LatestRepository(
            latestApi,
            currencyConverterDao,
            testDispatcher
        )

        val result = sut.getLatest(Constants.APP_ID)

        Assert.assertEquals(currencyConverterDao.getLatestInfoDB().first().license, result.license)


    }


    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    companion object {
        val FRESH_TIMEOUT = System.currentTimeMillis() / 1000
    }
}
