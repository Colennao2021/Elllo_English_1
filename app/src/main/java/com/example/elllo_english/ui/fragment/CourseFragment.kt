package com.example.elllo_english.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.elllo_english.R
import com.example.elllo_english.control.Repository
import com.example.elllo_english.models.Course
import com.example.elllo_english.ui.callback.ICLickCourse
import com.example.elllo_english.ui.adapter.CourseAdapter
import com.example.elllo_english.utils.AppData
import com.example.elllo_english.utils.AppLogger
import com.example.elllo_english.viewmodel.ViewModel

class CourseFragment : Fragment(), ICLickCourse {
    private lateinit var viewModel: ViewModel
    private lateinit var recycleView: RecyclerView
    private lateinit var updating: TextView
    private lateinit var warning: ImageView
    private lateinit var adapter: CourseAdapter

    private val args: CourseFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_course, container, false)
        recycleView = view.findViewById(R.id.recycleview)
        updating = view.findViewById(R.id.updating)
        warning = view.findViewById(R.id.warning)
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        AppLogger.info("Recycleview")
        adapter = CourseAdapter(this)
        recycleView.adapter = adapter
        recycleView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        AppLogger.info("ViewModel")
        Repository.levelId = args.level.id
        viewModel = ViewModelProvider(this).get(ViewModel::class.java)
        viewModel.getCourse().observe(viewLifecycleOwner, Observer { courses ->
            if (courses.isNotEmpty()) {
                updating.visibility = View.GONE
                warning.visibility = View.GONE
                recycleView.visibility = View.VISIBLE
                adapter.setListCourse(courses)
            } else {
                updating.visibility = View.VISIBLE
                warning.visibility = View.VISIBLE
            }
        })
    }

    override fun onClickCourse(course: Course) {
        val action = CourseFragmentDirections.actionCourseFragmentToDetailFragment(course)
        findNavController().navigate(action)
    }
}