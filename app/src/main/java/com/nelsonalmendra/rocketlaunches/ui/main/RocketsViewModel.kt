package com.nelsonalmendra.rocketlaunches.ui.main

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.nelsonalmendra.rocketlaunches.data.database.AppDatabase
import com.nelsonalmendra.rocketlaunches.data.Repository
import com.nelsonalmendra.rocketlaunches.data.database.Rocket
import com.nelsonalmendra.rocketlaunches.data.network.AppWebService
import java.util.concurrent.Executors

class RocketsViewModel(application: Application) :  AndroidViewModel(application) {

    private var repository: Repository

    var allRockets: LiveData<List<Rocket>>

    init {
        val rocketDao = AppDatabase.getDatabase(application).rocketDao()
        repository = Repository(rocketDao, AppWebService.create(), Executors.newSingleThreadExecutor())

        allRockets = repository.allRockets
    }

    fun refreshData() {
        repository.fetchRocketsList()
    }
}