package com.example.elllo_english.models

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Script")
data class Script(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    val id: Int,
    @NonNull
    val course :Int,
    @NonNull
    val audio: String,
    @NonNull
    val name: String,
    @NonNull
    val content: String
)