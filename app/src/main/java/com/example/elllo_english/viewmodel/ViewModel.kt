package com.example.elllo_english.viewmodel

import android.app.Application
import android.media.MediaPlayer
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.elllo_english.data.Database
import com.example.elllo_english.data.Repository
import com.example.elllo_english.data.models.Course
import com.example.elllo_english.data.models.Grammar
import com.example.elllo_english.data.models.Level
import com.example.elllo_english.data.models.Script

class ViewModel(application: Application) : AndroidViewModel(application) {
    var getALlLevel: LiveData<List<Level>>
    var getCourse: LiveData<List<Course>>
    var getGrammar: LiveData<List<Grammar>>
    var getScript: LiveData<List<Script>>
    var repository: Repository

    init {
        val dao = Database.getInstance(application).dao()
        repository = Repository(dao)
        getALlLevel = repository.getAllLevel
        getCourse = repository.getCourse
        getGrammar = repository.getGrammar
        getScript = repository.getScript
    }

    fun prepareAudio(mediaPlayer: MediaPlayer){
        repository.prepareAudio(mediaPlayer)
    }
}