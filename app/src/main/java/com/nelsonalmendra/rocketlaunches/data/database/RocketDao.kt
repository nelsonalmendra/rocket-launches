package com.nelsonalmendra.rocketlaunches.data.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
interface RocketDao {

    @Query("SELECT * FROM rocket")
    fun getAllRockets(): LiveData<List<Rocket>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(rockets: List<Rocket>)
}