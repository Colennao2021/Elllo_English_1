package com.example.elllo_english.control

import androidx.room.Dao
import androidx.room.Query
import com.example.elllo_english.models.Course
import com.example.elllo_english.models.Grammar
import com.example.elllo_english.models.Level
import com.example.elllo_english.models.Script

@Dao
interface DAO {
    @Query("select * from course where level = :levelId")
    suspend fun getCourse(levelId: Int): List<Course>

    @Query("select * from grammar where course = :courseId ")
    suspend fun getGrammar(courseId: Int): List<Grammar>

    @Query("select * from level")
    suspend fun getAllLevel(): List<Level>

    @Query("select * from script where course = :courseId")
    suspend fun getScript(courseId: Int): List<Script>

}