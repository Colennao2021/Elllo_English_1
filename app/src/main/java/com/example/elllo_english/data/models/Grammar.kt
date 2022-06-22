package com.example.elllo_english.data.models
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "grammar")
data class Grammar(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    val id: Int,
    @NonNull
    val course :Int,
    @NonNull
    val point: String,
    @NonNull
    val title: String,
    @NonNull
    val example: String
)