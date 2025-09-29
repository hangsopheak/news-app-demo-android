package com.rupp.newsapp.core.network

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Date

/**
 * A singleton object for managing the Retrofit instance.
 *
 * This object is responsible for configuring and providing a single instance of Retrofit
 * for the entire application, ensuring that network requests are handled consistently.
 *
 * It configures an [OkHttpClient] with the following interceptors:
 * - [headerInterceptor]: Adds custom headers, such as `X-DB-NAME`, to every outgoing request.
 * - [loggingInterceptor]: Logs the body of network requests and responses for debugging purposes.
 *   (This is particularly useful during development).
 *
 * The Retrofit instance is initialized lazily using `GsonConverterFactory` for JSON serialization/deserialization.
 *
 * @property retrofit The lazily-initialized Retrofit instance ready for use.
 */
object RetrofitInstance {
    private const val BASE_URL = "https://news-db-2.vercel.app/"

    //  Interceptor for adding custom headers

    private val headerInterceptor = Interceptor { chain ->
        val request = chain.request().newBuilder()
            .addHeader("X-DB-NAME",     "fdc77c85-002d-4fb8-992c-fd99a76b1323")
            .build()
        chain.proceed(request)
    }

    private val gson = GsonBuilder()
        .registerTypeAdapter(Date::class.java, DateAdapter())
        .create()

    // Logging interceptor (for debugging)
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(headerInterceptor)
        .addInterceptor(loggingInterceptor) // log requests/responses
        .build()

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient) // attach client with interceptors
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}