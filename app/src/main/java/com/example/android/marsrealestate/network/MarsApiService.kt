package com.example.android.marsrealestate.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))      // ScalarsConverter that supports strings and other primitive types
    .baseUrl(BASE_URL)
    .build()

/*  interface defines how Retrofit talks to the web server using HTTP requests */
interface MarsApiService {
    @GET("realestate")
    suspend fun getProperties(): List<MarsProperty>     // more efficient and easier to read if you could use coroutines with exception handling, instead of using callbacks
}

/* define a public object called MarsApi to initialize the Retrofit service.
            This is a standard Kotlin code pattern to use when creating a service object.  */
object MarsApi {
    val retrofitService : MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java) }
}

