package com.example.elllo_english.utils

import com.example.elllo_english.models.Course
import com.example.elllo_english.models.Grammar
import com.example.elllo_english.models.Script

object AppData {
    const val DATABASE_NAME: String = "my_elllo_english.db"
    const val DB_PATH: String = "database/elllo.db"
    const val FRAGMENT_COUNT: Int = 2
    const val SCRIPT: Int = 0
    const val GRAMMAR: Int = 1
    const val DURATION = 0
    const val CURRENT_POSITION = 1

    val TITLE: List<String> = listOf("Script", "Grammar")
    val COURSE_DEFAULT = listOf<Course>()
    val GRAMMAR_DEFAULT = listOf<Grammar>()
    val SCRIPT_DEFAULT = listOf<Script>()
}