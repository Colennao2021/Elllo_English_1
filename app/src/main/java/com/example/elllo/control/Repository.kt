package com.example.elllo.control

import com.example.elllo.models.Course
import com.example.elllo.models.Grammar
import com.example.elllo.models.Level
import com.example.elllo.models.Script

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