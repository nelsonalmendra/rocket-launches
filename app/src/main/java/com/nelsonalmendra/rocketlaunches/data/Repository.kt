package com.nelsonalmendra.rocketlaunches.data

import android.arch.lifecycle.LiveData
import com.nelsonalmendra.rocketlaunches.data.database.Rocket
import com.nelsonalmendra.rocketlaunches.data.database.RocketDao
import com.nelsonalmendra.rocketlaunches.data.network.AppWebService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executor

class Repository(private val rocketDao: RocketDao, private val appWebService: AppWebService, private val executor: Executor) {

    val allRockets: LiveData<List<Rocket>>
        get() {
            fetchRocketsList()
            return rocketDao.getAllRockets()
        }

    fun fetchRocketsList(){
        executor.execute {
            appWebService.getRockets().enqueue(object : Callback<List<Rocket>> {

                override fun onResponse(call: Call<List<Rocket>>?, response: Response<List<Rocket>>?) {
                    val rockets: List<Rocket>? = response?.body()
                    if(rockets != null)
                        executor.execute { rocketDao.insertAll(rockets) }
                }

                override fun onFailure(call: Call<List<Rocket>>?, t: Throwable?) {}
            })
        }
    }
}