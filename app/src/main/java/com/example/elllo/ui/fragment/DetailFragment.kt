package com.example.elllo.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.example.elllo.R
import com.example.elllo.control.Repository
import com.example.elllo.ui.adapter.ViewPagerAdapter
import com.example.elllo.utils.AppData
import com.example.elllo.utils.AppLogger
import com.example.elllo.viewmodel.ViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailFragment : Fragment() {
    private lateinit var play: Button
    private lateinit var seekBar: SeekBar
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var viewModel: ViewModel

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

        AppLogger.info("Update courseId for repository")
        loadCourseID()

        AppLogger.info("Prepare audio from url")
        loadAudio()

        AppLogger.info("Setting show tablayout ")
        loadTablayout()
    }

    private fun loadCourseID() {
        GrammarFragment.courseId = args.course.id
        ScriptFragment.courseId = args.course.id
    }

    private fun loadTablayout() {
        viewPager.adapter = ViewPagerAdapter(requireActivity())
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = AppData.TITLE[position]
        }.attach()
    }

    private fun loadAudio() {
        var isStarted = false

        seekBar.max = 100

        AppLogger.info("Load mediaPlayer.currenPosition from viewModel for seekbar")
        Repository.courseId = args.course.id
        viewModel = ViewModelProvider(this).get(ViewModel::class.java)
        viewModel.seekBarProcess.observe(viewLifecycleOwner, Observer { temp ->
            seekBar.progress = temp
        })

        AppLogger.info("Get link audio")
        viewModel.getScript().observe(viewLifecycleOwner, Observer { temps ->
            if (temps.isEmpty()) {
                AppLogger.info("Db url lỗi play không pause được")
                viewModel.prepareAudio(temps[0].audio)
            } else {
                viewModel.prepareAudio(AppData.URL_DEFAULT)
            }
        })

        AppLogger.info("Play audio")
        play.setOnClickListener {
            if (!isStarted) {
                play.setBackgroundResource(R.drawable.ic_baseline_pause)
                viewModel.start()
                isStarted = true
            } else if (isStarted) {
                play.setBackgroundResource(R.drawable.ic_baseline_play)
                viewModel.pause()
                isStarted = false
            }
        }

        AppLogger.info("Audio touch from user")
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    val temp = (progress / 100.0) * viewModel.mediaPlayer.duration
                    viewModel.mediaPlayer.seekTo(temp.toInt())
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                //TODO("Not yet implemented")
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                //TODO("Not yet implemented")
            }
        })
    }
}