package com.nelsonalmendra.rocketlaunches.data.network

import com.nelsonalmendra.rocketlaunches.data.database.Rocket
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface AppWebService {

    @GET("rockets")
    fun getRockets() : Call<List<Rocket>>

    companion object Factory {
        fun create() : AppWebService {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(MoshiConverterFactory.create())
                    .baseUrl("https://api.spacexdata.com/v2/")
                    .build()

            return retrofit.create(AppWebService::class.java)
        }
    }
}