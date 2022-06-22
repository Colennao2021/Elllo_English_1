package com.example.elllo_english.ui.fragment

import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SeekBar
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.example.elllo_english.R
import com.example.elllo_english.data.models.Script
import com.example.elllo_english.ui.adapter.ViewPagerAdapter
import com.example.elllo_english.utils.AppLogger
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.roundToInt

class DetailFragment : Fragment() {
    private lateinit var play: Button
    private lateinit var seekBar: SeekBar
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var timer: Timer
    private lateinit var titles: ArrayList<String>

    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        play = view.findViewById(R.id.play)
        seekBar = view.findViewById(R.id.seek_bar)
        viewPager = view.findViewById(R.id.view_pager)
        tabLayout = view.findViewById(R.id.tab_layout)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadAudio()
        loadTablayout()
        loadCourseID()
    }

    private fun loadAudio() {
        var isPrepare = false
        var isStarted = false

        AppLogger.info("MediaPlayer")
        val mediaPlayer = MediaPlayer()
        val audioUrl =
            "https://data.chiasenhac.com/down2/2256/1/2255882-3e008551/128/Chi%20La%20Mot%20Cuoc%20Gap%20Go%20-%20Ham%20Dang_%20Nam%20T.mp3"
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
        mediaPlayer.setDataSource(audioUrl)
        mediaPlayer.prepareAsync()

        seekBar.progress = 0
        seekBar.max = 100
        timer = Timer()

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
            if (isStarted == false) {
                play.setBackgroundResource(R.drawable.ic_baseline_pause)

                if (isPrepare && !isStarted) {
                    isStarted = true
                    mediaPlayer.start()
                }
                Toast.makeText(requireContext(),"Audio Play",Toast.LENGTH_SHORT).show()
            } else if (isStarted == true) {
                play.setBackgroundResource(R.drawable.ic_baseline_play)
                isStarted = false
                mediaPlayer.pause()
                Toast.makeText(requireContext(),"Audio Pause",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadCourseID() {
        GrammarFragment.courseId = args.course.id
        ScriptFragment.courseId = args.course.id
    }

    private fun loadTablayout() {
        titles = ArrayList()
        titles.add("Script")
        titles.add("Grammar")

        viewPager.adapter = ViewPagerAdapter(requireActivity())
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = titles.get(position)
        }.attach()
    }

    override fun onDestroyView() {
        timer.cancel()
        super.onDestroyView()
    }
}