package com.example.currencyconverter.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.currencyconverter.currencyFeature.model.LatestModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.*
import javax.inject.Inject

@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
@HiltAndroidTest
class CurrencyConverterDaoTest {

    @get:Rule
    val initExecutorRule = InstantTaskExecutorRule() //needed for background executor management

    @get:Rule
    val hiltAndroidRule = HiltAndroidRule(this) // needed for injecting


    @Inject
    lateinit var currencyConverterDatabase: CurrencyConverterDatabase //injecting db using hilt

    @Inject
    lateinit var currencyConverterDao: CurrencyConverterDao

    @Before
    fun setup() {
        hiltAndroidRule.inject()
    }

    @Test
    fun insertEmptyLatestModel_CheckBaseisEqualUSD() = runTest {
        //arrange
        currencyConverterDao.deleteAll()
        val latestmodel = LatestModel()


        //act
        currencyConverterDao.saveLatestModel(latestmodel)
        val result = currencyConverterDao.getLatestInfoDB()

        //assert
        Assert.assertEquals("USD", result.first().base)
    }


    @Test
    fun insertLFD_CheckBaseNotEqualUSD() = runTest {
        //arrange
        currencyConverterDao.deleteAll()
        val latestmodel = LatestModel("LFD")


        //act
        currencyConverterDao.saveLatestModel(latestmodel)
        val result = currencyConverterDao.getLatestInfoDB()

        //assert
        Assert.assertNotEquals("USD", result.first().base)
    }

    @Test
    fun insertBaseLFD_CheckBaseisLFD() = runTest {
        //arrange
        currencyConverterDao.deleteAll()
        val latestmodel3 = LatestModel("LFD")

        //act
        currencyConverterDao.saveLatestModel(latestmodel3)
        val result = currencyConverterDao.getLatestInfoDB()

        //assert
        Assert.assertEquals("LFD", result.first().base)
    }

    @After
    fun closeDatabase() {
        currencyConverterDatabase.close()
    }
}