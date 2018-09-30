package com.nelsonalmendra.rocketlaunches.data.database

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Rocket(
        @PrimaryKey
        val id: String,
        val name: String,
        val country: String,
        @Embedded
        val engines: Engines,
        val active: Boolean
) : Parcelable

@Parcelize
data class Engines(
        val number: Int
) : Parcelable