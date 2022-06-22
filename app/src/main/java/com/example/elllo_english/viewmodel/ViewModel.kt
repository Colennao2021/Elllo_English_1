package com.example.elllo_english.viewmodel

import android.app.Application
import android.media.AudioManager
import android.media.MediaPlayer
import android.util.Log
import android.widget.Button
import android.widget.SeekBar
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.elllo_english.R
import com.example.elllo_english.data.Database
import com.example.elllo_english.data.Repository
import com.example.elllo_english.data.models.Course
import com.example.elllo_english.data.models.Grammar
import com.example.elllo_english.data.models.Level
import com.example.elllo_english.data.models.Script
import com.example.elllo_english.utils.AppLogger
import java.util.*
import kotlin.math.roundToInt

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

    fun prepareAudio(seekBar: SeekBar, play: Button, timer: Timer) {
        var isPrepare = false
        var isStarted = false

        AppLogger.info("Media prepare")
        val mediaPlayer = MediaPlayer()
        val audioUrl =
            "https://data.chiasenhac.com/down2/2255/3/2254501-49ffc50b/128/Somebody%20Like%20U%20-%20Alan%20Walker_%20Au_Ra.mp3"
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
        mediaPlayer.setDataSource(audioUrl)
        mediaPlayer.prepareAsync()

        seekBar.progress = 0
        seekBar.max = 100

        AppLogger.info("MediaPlayer prepare success")
        mediaPlayer.setOnPreparedListener {
            isPrepare = true
            val timerTask = object : TimerTask() {
                override fun run() {
                    if (mediaPlayer.duration > 0)
                        seekBar.progress =
                            ((mediaPlayer.currentPosition.toFloat() / mediaPlayer.duration.toFloat()) * 100.0f).roundToInt()
                    Log.d("tag", mediaPlayer.currentPosition.toString())
                }
            }
            timer.schedule(timerTask, 0, 1000)
        }

        AppLogger.info("Play")
        play.setOnClickListener {
            if (!isStarted) {
                play.setBackgroundResource(R.drawable.ic_baseline_pause)

                if (isPrepare && !isStarted) {
                    isStarted = true
                    mediaPlayer.start()
                }
            } else if (isStarted) {
                play.setBackgroundResource(R.drawable.ic_baseline_play)
                isStarted = false
                mediaPlayer.pause()
            }
        }
    }
}