package com.example.elllo_english.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.elllo_english.R
import com.example.elllo_english.models.Grammar

class GrammarAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var datas: List<Grammar> = emptyList()

    fun setListGrammar(tmp: List<Grammar>) {
        this.datas = tmp
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.item_grammar, parent, false)
        return GrammarViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is GrammarViewHolder) {
            val currentGrammar = datas[position]
            holder.point.text = currentGrammar.point
            holder.title.text = currentGrammar.title
            holder.example.text = currentGrammar.example
        }
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    class GrammarViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var point: TextView = itemView.findViewById(R.id.point_grammar)
        var title: TextView = itemView.findViewById(R.id.title_grammar)
        var example: TextView = itemView.findViewById(R.id.example_grammar)
    }
}