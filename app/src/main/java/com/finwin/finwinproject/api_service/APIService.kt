package com.finwin.finwinproject.api_service

import com.finwin.finwinproject.BuildConfig
import com.finwin.finwinproject.config.ApplicationConstants
import com.finwin.finwinproject.view.home_screen.model.DetailsResponseModel
import com.finwin.finwinproject.view.home_screen.model.DetailsResponseModelItem
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

interface APIService {

    object Factory {
        fun create(): APIService {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            } else {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
            }
            val gson = GsonBuilder()
                .setLenient()
                .create()
            val client = OkHttpClient.Builder()
                .readTimeout(
                    ApplicationConstants.TIMEOUT_VALUE.toLong(),
                    TimeUnit.MILLISECONDS
                )
                .writeTimeout(
                    ApplicationConstants.TIMEOUT_VALUE.toLong(),
                    TimeUnit.MILLISECONDS
                )
                .addInterceptor(httpLoggingInterceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(ApplicationConstants.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()
            return retrofit.create(APIService::class.java)
        }
    }


    @GET("photos")
    fun getPhotos(
    ): Call<DetailsResponseModel?>?

    @GET("photos/{photoId}")
    fun getDetailsPhotos(
        @Path("photoId") id: Int
    ): Call<DetailsResponseModelItem?>?

//    @GET
//    fun getDetails(
//        @Path name:String
//    ): Call<DetailsResponseModel?>?

}
