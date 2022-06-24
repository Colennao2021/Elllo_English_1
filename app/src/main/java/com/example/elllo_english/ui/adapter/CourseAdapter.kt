package com.example.elllo_english.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.elllo_english.R
import com.example.elllo_english.models.Course
import com.example.elllo_english.ui.callback.ICLickCourse
import kotlinx.android.synthetic.main.item_course.view.*

class CourseAdapter(private val icLickCourse: ICLickCourse) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var datas = emptyList<Course>()

    fun setListCourse(listCourse: List<Course>) {
        this.datas = listCourse
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.item_course, parent, false)
        return CourseViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CourseViewHolder) {
            val currentCourse = datas[position]
            holder.name.text = currentCourse.name
            holder.itemView.item_course.setOnClickListener {
                icLickCourse.onClickCourse(currentCourse)
            }
        }
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.name_course)
    }
}