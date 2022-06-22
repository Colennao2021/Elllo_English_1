package com.example.elllo_english.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.elllo_english.R
import com.example.elllo_english.data.models.Script
import kotlinx.android.synthetic.main.item_script.view.*

class ScriptAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var scripts: List<Script> = emptyList()

    fun setListScript(tmp: List<Script>) {
        this.scripts = tmp
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.item_script, parent, false)
        return ScriptViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ScriptViewHolder) {
            val script = scripts[position]
            holder.bindingData(script)
        }
    }

    override fun getItemCount(): Int {
        return scripts.size
    }

    class ScriptViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name: TextView = itemView.name_script
        private val content: TextView = itemView.content_script

        fun bindingData(script: Script) {
            name.text = script.name
            content.text = script.content
        }
    }
}