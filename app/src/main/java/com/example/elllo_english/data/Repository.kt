package com.example.elllo_english.data

import android.media.AudioManager
import android.media.MediaPlayer
import androidx.lifecycle.LiveData
import com.example.elllo_english.data.models.Course
import com.example.elllo_english.data.models.Grammar
import com.example.elllo_english.data.models.Level
import com.example.elllo_english.data.models.Script

class Repository(private val dao: DAO) {
    companion object {
        var levelId: Int = 0
        var courseId: Int = 0
    }

    val getAllLevel: LiveData<List<Level>> = dao.getAllLevel()

    val getCourse: LiveData<List<Course>> = dao.getCourse(levelId)

    val getGrammar: LiveData<List<Grammar>> = dao.getGrammar(courseId)

    val getScript: LiveData<List<Script>> = dao.getScript(courseId)

}