package com.example.elllo.control

import androidx.room.Dao
import androidx.room.Query
import com.example.elllo.models.Course
import com.example.elllo.models.Grammar
import com.example.elllo.models.Level
import com.example.elllo.models.Script

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