package com.example.data

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.example.domain.models.GetPhotosUseCase
import com.slack.eithernet.ApiResultCallAdapterFactory
import com.slack.eithernet.ApiResultConverterFactory
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.net.HttpURLConnection

class GetPhotosUseCaseTest {

    private var mockWebServer = MockWebServer()
    private lateinit var photoService: PhotoService

    @Before
    fun setup() {
        mockWebServer.start()
        photoService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .client(OkHttpClient())
            .addConverterFactory(ApiResultConverterFactory)
            .addCallAdapterFactory(ApiResultCallAdapterFactory)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(PhotoService::class.java)
    }
    @After
    fun teardown() {
        mockWebServer.shutdown()
    }
    @Test
    fun testGetPhotos() = runTest {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(TestData.getTestResponseString())
        mockWebServer.enqueue(response)
        val repository = PhotoRepositoryDefault(photoService)
        val getPhotosUseCase = GetPhotosUseCase(repository)

        val result = getPhotosUseCase().getResultOrNull()!!
        assertThat(result.size).isEqualTo(993)
    }
}