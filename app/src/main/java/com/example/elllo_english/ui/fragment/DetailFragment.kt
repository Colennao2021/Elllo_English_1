package com.example.elllo_english.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.example.elllo_english.R
import com.example.elllo_english.ui.adapter.ViewPagerAdapter
import com.example.elllo_english.utils.AppLogger
import com.example.elllo_english.viewmodel.ViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import java.util.*

class DetailFragment : Fragment() {
    private lateinit var play: Button
    private lateinit var seekBar: SeekBar
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var titles: ArrayList<String>
    private lateinit var viewModel: ViewModel
    private lateinit var timer: Timer

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

        loadCourseID()
        loadAudio()
        loadTablayout()
    }

    private fun loadAudio() {
        timer =Timer()

        AppLogger.info("MediaPlayer")
        viewModel=ViewModelProvider(this).get(ViewModel::class.java)
        viewModel.prepareAudio(seekBar,play,timer)
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
            tab.text = titles[position]
        }.attach()
    }

    override fun onDestroyView() {
        timer.cancel()
        super.onDestroyView()
    }
}