package com

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.currencyconverter.currencies.model.LatestModel
import com.example.currencyconverter.db.CurrencyConverterDao
import com.example.currencyconverter.db.CurrencyConverterDatabase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
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
    lateinit var currencyConverterDatabase: CurrencyConverterDatabase


    @Inject
    lateinit var currencyConverterDao: CurrencyConverterDao

    @Before
    fun setup() {
        hiltAndroidRule.inject()
    }

    @Test
    fun insertEmptyLatesModel_CheckBase_USD() = runTest {
        //arrange
        currencyConverterDao.deleteAll()
        val latestmodel = LatestModel()


        //act
        currencyConverterDao.saveLatestModel(latestmodel)
        val result = currencyConverterDao.getLatestInfoDB()

        //assert
        Assert.assertEquals("USD", result.base)
    }


    @Test
    fun insertLFD_NotEqualUSD() = runTest {
        //arrange
        currencyConverterDao.deleteAll()
        val latestmodel = LatestModel("LFD")


        //act
        currencyConverterDao.saveLatestModel(latestmodel)
        val result = currencyConverterDao.getLatestInfoDB()

        //assert
        Assert.assertNotEquals("USD", result.base)
    }

    @Test
    fun insertBase_CheckBase_Correct() = runTest {
        //arrange
        currencyConverterDao.deleteAll()
        val latestmodel3 = LatestModel("LFD")

        //act
        currencyConverterDao.saveLatestModel(latestmodel3)
        val result = currencyConverterDao.getLatestInfoDB()

        //assert
        Assert.assertEquals("LFD", result.base)
    }

    @After
    fun closeDatabase() {
        currencyConverterDatabase.close()
    }
}