package com.example.elllo_english.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.elllo_english.R
import com.example.elllo_english.data.Repository
import com.example.elllo_english.data.models.Script
import com.example.elllo_english.ui.adapter.ScriptAdapter
import com.example.elllo_english.utils.AppLogger
import com.example.elllo_english.viewmodel.ViewModel

class ScriptFragment() : Fragment() {
    private lateinit var viewModel: ViewModel
    private lateinit var recycleView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_script, container, false)
        recycleView = view.findViewById(R.id.script_recycleview)
        return view
    }

    companion object {
        var courseId = 0
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        AppLogger.info("RecycleView")
        val adapter = ScriptAdapter()
        recycleView.adapter = adapter
        recycleView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        AppLogger.info("ViewModel get script")
        Repository.courseId = courseId
        viewModel = ViewModelProvider(this).get(ViewModel::class.java)
        viewModel.getScript.observe(viewLifecycleOwner, Observer { scripts ->
            if (scripts.isNotEmpty()) {
                adapter.setListScript(scripts)
            } else {
                AppLogger.info("Default script if null")
                val listDefault: List<Script> = listOf(Script(0, 0, "", "Loading...", ""))
                adapter.setListScript(listDefault)
            }
        })

    }

}