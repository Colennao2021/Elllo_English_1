package com.example.elllo_english.models

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "course")
data class Course(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    val id: Int,
    @NonNull
    val level: Int=0,
    @NonNull
    val name: String="",
    @NonNull
    val url :String="",
    @NonNull
    val state :Int=0
) :Parcelable