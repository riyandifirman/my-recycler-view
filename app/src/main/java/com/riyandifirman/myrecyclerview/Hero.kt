package com.riyandifirman.myrecyclerview

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

// Membuat data class Hero yang diparcelize
@Parcelize
data class Hero(
    val name: String,
    val description: String,
    val photo: Int
) : Parcelable
