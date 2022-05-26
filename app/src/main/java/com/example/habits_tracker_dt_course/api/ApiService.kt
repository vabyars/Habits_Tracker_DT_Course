package com.example.habits_tracker_dt_course.api

import com.example.habits_tracker_dt_course.model.ServerHabit
import com.example.habits_tracker_dt_course.model.Uid
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

interface ApiService {

    @GET("api/habit")
    suspend fun getHabits(
    ): Response<List<ServerHabit>>

    @HTTP(method = "DELETE", path = "api/habit", hasBody = true)
    suspend fun deleteHabit(
        @Body uid: Uid
    ): Response<Unit>


    @PUT("api/habit")
    suspend fun putHabit(
        @Body habit: ServerHabit
    ): Response<Uid>

    companion object Factory {

        private const val API_BASE_URL = "https://droid-test-server.doubletapp.ru/"
        private const val CONTENT_TYPE = "application/json"
        private const val API_KEY = "5024d8d0-f09e-49d1-8e0f-43b02f5630e9"

        fun create(): Retrofit {

            val client = OkHttpClient.Builder()
                .addInterceptor { chain ->
                    chain.proceed(
                        chain
                            .request().newBuilder()
                            .addHeader("accept", CONTENT_TYPE)
                            .addHeader("Authorization", API_KEY)
                            .addHeader("Content-Type", CONTENT_TYPE)
                            .build()
                    )
                }
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build()

            val gson = GsonBuilder()
                .registerTypeAdapter(
                    ServerHabit::class.java,
                    ServerHabit.HabitJsonDeserializer()
                )
                .create()

            val retrofit = Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()


            return retrofit
        }
    }

}