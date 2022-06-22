package com.example.elllo_english.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.elllo_english.data.models.Course
import com.example.elllo_english.data.models.Grammar
import com.example.elllo_english.data.models.Level
import com.example.elllo_english.data.models.Script

@Dao
interface DAO {

    @Query("select * from course where level = :levelId")
    fun getCourse(levelId: Int): LiveData<List<Course>>

    @Query("select * from grammar where course = :courseId ")
    fun getGrammar(courseId: Int): LiveData<List<Grammar>>

    @Query("select * from level")
    fun getAllLevel(): LiveData<List<Level>>

    @Query("select * from script where course = :courseId")
    fun getScript(courseId: Int): LiveData<List<Script>>

}