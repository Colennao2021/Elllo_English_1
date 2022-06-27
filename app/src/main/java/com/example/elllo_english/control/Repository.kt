package com.example.elllo_english.control

import com.example.elllo_english.models.Course
import com.example.elllo_english.models.Grammar
import com.example.elllo_english.models.Level
import com.example.elllo_english.models.Script

class Repository(private val dao: DAO) {
    companion object {
        var levelId: Int = 0
        var courseId: Int = 0
    }

    suspend fun getALlLevel(): List<Level> {
        return dao.getAllLevel()
    }

    suspend fun getCourse(): List<Course> {
        return dao.getCourse(levelId)
    }

    suspend fun getGrammar(): List<Grammar> {
        return dao.getGrammar(courseId)
    }

    suspend fun getScript(): List<Script> {
        return dao.getScript(courseId)
    }
}