package com.example.android.rockets.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Rocket(
    val id: Int,
    val stages: Int,
    val rocketName: String,
    val imageUrlString: String,
    val wikiUrlString: String,
    val description: String,
    val height: Double,
    val heightFeet: Double
) : Parcelable

