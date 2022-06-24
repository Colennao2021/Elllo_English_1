package com.example.elllo_english.viewmodel

import android.app.Application
import android.media.AudioManager
import android.media.MediaPlayer
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.elllo_english.control.Database
import com.example.elllo_english.control.Repository
import com.example.elllo_english.models.Course
import com.example.elllo_english.models.Grammar
import com.example.elllo_english.models.Level
import com.example.elllo_english.models.Script
import com.example.elllo_english.utils.AppData
import com.example.elllo_english.utils.AppLogger
import kotlinx.coroutines.launch
import java.util.*
import kotlin.math.roundToInt

class ViewModel(application: Application) : AndroidViewModel(application) {
    var mediaPlayer: MediaPlayer
    var seekBarProcess: MutableLiveData<Int>
    var repository: Repository

    init {
        val dao = Database.getInstance(application).dao()
        repository = Repository(dao)
        seekBarProcess = MutableLiveData()
        mediaPlayer = MediaPlayer()
    }

    fun getAllLevel(): MutableLiveData<List<Level>> {
        val levels: MutableLiveData<List<Level>> = MutableLiveData()
        viewModelScope.launch {
            if (repository.getALlLevel().isNotEmpty()) {
                levels.postValue(repository.getALlLevel())
            } else {
                levels.postValue(null)
            }
        }
        return levels
    }

    fun getCourse(): MutableLiveData<List<Course>> {
        val courses: MutableLiveData<List<Course>> = MutableLiveData()
        viewModelScope.launch {
            if (repository.getCourse().isNotEmpty()) {
                courses.postValue(repository.getCourse())
            } else {
                courses.postValue(AppData.COURSE_DEFAULT)
            }
        }
        return courses
    }

    fun getGrammar(): MutableLiveData<List<Grammar>> {
        val grammars: MutableLiveData<List<Grammar>> = MutableLiveData()
        viewModelScope.launch {
            if (repository.getGrammar().isNotEmpty()) {
                grammars.postValue(repository.getGrammar())
            } else {
                grammars.postValue(AppData.GRAMMAR_DEFAULT)
            }
        }
        return grammars
    }

    fun getScript(): MutableLiveData<List<Script>> {
        val scripts: MutableLiveData<List<Script>> = MutableLiveData()
        viewModelScope.launch {
            if (repository.getScript().isNotEmpty()) {
                scripts.postValue(repository.getScript())
            } else {
                scripts.postValue(AppData.SCRIPT_DEFAULT)
            }
        }
        return scripts
    }

    fun prepareAudio(timer: Timer) {
        AppLogger.info("MediaPlayer")

        val audioUrl =
            "https://data.chiasenhac.com/down2/2258/5/2257592-de5bf580/128/Thi%20Tuu%20Hoan%20Nguyet%20Quang%20-%20Tham%20Mat%20Nha.mp3"
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
        mediaPlayer.setDataSource(audioUrl)
        mediaPlayer.prepareAsync()

        AppLogger.info("MediaPlayer prepare success")
        mediaPlayer.setOnPreparedListener {
            val timerTask = object : TimerTask() {
                override fun run() {
                    if (mediaPlayer.duration > 0)
                        seekBarProcess.postValue(((mediaPlayer.currentPosition.toFloat() / mediaPlayer.duration.toFloat()) * 100.0f).roundToInt())
                    Log.d("tag", mediaPlayer.currentPosition.toString())
                }
            }
            timer.schedule(timerTask, 0, 500)
        }
    }

    fun start() {
        mediaPlayer.start()
    }

    fun pause() {
        mediaPlayer.pause()
    }
}