package com.example.elllo.viewmodel

import android.app.Application
import android.media.AudioManager
import android.media.MediaPlayer
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.elllo.control.Database
import com.example.elllo.control.Repository
import com.example.elllo.models.Course
import com.example.elllo.models.Grammar
import com.example.elllo.models.Level
import com.example.elllo.models.Script
import com.example.elllo.utils.AppData
import com.example.elllo.utils.AppLogger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.math.roundToInt

class ViewModel(application: Application) : AndroidViewModel(application) {
    private lateinit var timer: Timer

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
        viewModelScope.launch(Dispatchers.IO) {
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
        viewModelScope.launch(Dispatchers.IO) {
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
        viewModelScope.launch(Dispatchers.IO) {
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
        viewModelScope.launch(Dispatchers.IO) {
            if (repository.getScript().isNotEmpty()) {
                scripts.postValue(repository.getScript())
            } else {
                scripts.postValue(AppData.SCRIPT_DEFAULT)
            }
        }
        return scripts
    }

    fun prepareAudio(url: String) {
        AppLogger.info("MediaPlayer prepare background thread")

        viewModelScope.launch(Dispatchers.IO) {
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
            mediaPlayer.setDataSource(url)
            mediaPlayer.prepareAsync()

            AppLogger.info("MediaPlayer prepare success")
            timer = Timer()
            mediaPlayer.setOnPreparedListener {
                val timerTask = object : TimerTask() {
                    override fun run() {
                        if (mediaPlayer.duration > 0)
                            seekBarProcess.postValue(((mediaPlayer.currentPosition.toFloat() / mediaPlayer.duration.toFloat()) * 100.0f).roundToInt())
                    }
                }
                timer.schedule(timerTask, 0, 200)
            }
        }
    }

    fun start() {
        AppLogger.info("Audio start")
        mediaPlayer.start()
    }

    fun pause() {
        AppLogger.info("Audio pause")
        mediaPlayer.pause()
    }
}