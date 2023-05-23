package com.example.currencyconverter.currencyFeature

import com.example.currencyconverter.common.Constants
import com.example.currencyconverter.currencyFeature.service.ILatestService
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LatestApiTest {

    lateinit var mockWebServer: MockWebServer
    lateinit var latestApi: ILatestService

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        latestApi = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ILatestService::class.java)

    }

    @Test

    fun getProductsEmptyBodyResponse() = runTest {
        val mockResponse = MockResponse()
        mockResponse.setBody("{}")
        mockWebServer.enqueue(mockResponse)

        val response = latestApi.getLatest(Constants.APP_ID)

        mockWebServer.takeRequest()
        Assert.assertEquals(true, response.body()?.base ?: "USD" == "USD")
    }

    @Test
    fun getProductsNullBodyErrorCode() = runTest {
        val mockResponse = MockResponse()
        mockResponse.setResponseCode(400)
        mockResponse.setBody("")
        mockWebServer.enqueue(mockResponse)

        val response = latestApi.getLatest(Constants.APP_ID)

        mockWebServer.takeRequest()
        Assert.assertEquals(400, response.code())
        Assert.assertEquals(false, response.isSuccessful)
        Assert.assertEquals(null, response.body())
    }

    @Test
    fun getProducts_ReturnLatestModel() = runTest {
        val mockResponse = MockResponse()
        val content = Helper.readFileResource("/response.json")
        mockResponse.setBody(content)
        mockResponse.setResponseCode(200)
        mockWebServer.enqueue(mockResponse)

        val response = latestApi.getLatest(Constants.APP_ID)

        mockWebServer.takeRequest()
        Assert.assertEquals("OK", response.message())
        Assert.assertEquals(true, response.isSuccessful)
        Assert.assertEquals(1684854000, response.body()?.timestamp?:-1)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

}