package com.example.elllo_english.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toolbar
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.elllo_english.R
import com.example.elllo_english.data.models.Level
import com.example.elllo_english.ui.fragment.LevelFragmentDirections
import kotlinx.android.synthetic.main.item_level.view.*

class LevelAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var levels = emptyList<Level>()

    fun setListLevel(listLevel: List<Level>) {
        this.levels = listLevel
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.item_level, parent, false)
        return LevelViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is LevelViewHolder) {
            val level = levels[position]
            holder.bindingData(level)
            holder.itemView.item_level.hoc_ngay.setOnClickListener {
                val action =
                    LevelFragmentDirections.actionLevelFragmentToCourseFragment(level)
                holder.itemView.findNavController().navigate(action)
            }
        }
    }

    override fun getItemCount(): Int {
        return levels.size
    }

    class LevelViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name: TextView = itemView.name_level
        private val image: ImageView = itemView.image_level

        fun bindingData(level: Level) {
            val url = level.image
            name.text = level.name
            Glide.with(itemView).load(url).into(image)
        }
    }
}