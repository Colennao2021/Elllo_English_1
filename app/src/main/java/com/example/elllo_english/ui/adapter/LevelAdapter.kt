package com.example.elllo_english.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.elllo_english.R
import com.example.elllo_english.models.Level
import com.example.elllo_english.ui.callback.IClickLevel
import kotlinx.android.synthetic.main.item_level.view.*

class LevelAdapter(private val iCLickLevel: IClickLevel) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var data = emptyList<Level>()

    fun setListLevel(listLevel: List<Level>) {
        this.data = listLevel
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.item_level, parent, false)
        return LevelViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is LevelViewHolder) {
            val level = data[position]
            val url = level.image
            holder.name.text = level.name
            Glide.with(holder.itemView).load(url).into(holder.image)

            holder.itemView.item_level.study.setOnClickListener {
                iCLickLevel.onClickLevel(level)
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class LevelViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.name_level
        val image: ImageView = itemView.image_level
    }
}