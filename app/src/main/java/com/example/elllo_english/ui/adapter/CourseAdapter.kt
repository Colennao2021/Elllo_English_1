package com.example.elllo_english.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.elllo_english.R
import com.example.elllo_english.data.models.Course
import com.example.elllo_english.ui.fragment.CourseFragmentDirections
import kotlinx.android.synthetic.main.item_course.view.*

class CourseAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var courses = emptyList<Course>()

    fun setListCourse(listCourse: List<Course>) {
        this.courses = listCourse
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.item_course, parent, false)
        return CourseViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CourseViewHolder) {
            val course = courses[position]
            holder.bindingData(course)
            holder.itemView.item_course.setOnClickListener {
                val action = CourseFragmentDirections.actionCourseFragmentToDetailFragment(course)
                holder.itemView.findNavController().navigate(action)
            }
        }
    }

    override fun getItemCount(): Int {
        return courses.size
    }

    class CourseViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name: TextView = itemView.name_course

        fun bindingData(course: Course) {
            name.text = course.name
        }
    }
}