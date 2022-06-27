package com.example.elllo.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.elllo.R
import com.example.elllo.models.Script
import kotlinx.android.synthetic.main.item_script.view.*

class ScriptAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var data: List<Script> = emptyList()

    fun setListScript(tmp: List<Script>) {
        this.data = tmp
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.item_script, parent, false)
        return ScriptViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ScriptViewHolder) {
            val currentScript = data[position]
            holder.name.text = currentScript.name
            holder.content.text = currentScript.content
            if (currentScript.name == "Todd") {
                holder.itemView.item_script.setBackgroundResource(R.color.persion1)
            } else {
                holder.itemView.item_script.setBackgroundResource(R.color.persion2)
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ScriptViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.name_script)
        var content: TextView = itemView.findViewById(R.id.content_script)
    }
}