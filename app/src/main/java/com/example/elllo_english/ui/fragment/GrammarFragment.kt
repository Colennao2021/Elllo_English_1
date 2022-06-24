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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.elllo_english.R
import com.example.elllo_english.control.Repository
import com.example.elllo_english.ui.adapter.GrammarAdapter
import com.example.elllo_english.utils.AppLogger
import com.example.elllo_english.viewmodel.ViewModel

class GrammarFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: ViewModel
    private lateinit var updating: TextView
    private lateinit var warning: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_grammar, container, false)
        recyclerView = view.findViewById(R.id.recycleview)
        updating = view.findViewById(R.id.updating)
        warning = view.findViewById(R.id.warning)
        return view
    }

    companion object {
        var courseId = 0
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        AppLogger.info("Recycleview")
        val adapter = GrammarAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        AppLogger.info("ViewModel get grammar")
        Repository.courseId = courseId
        viewModel = ViewModelProvider(this).get(ViewModel::class.java)
        viewModel.getGrammar().observe(viewLifecycleOwner, Observer { grammars ->
            if (grammars.isNotEmpty()) {
                recyclerView.visibility = View.VISIBLE
                updating.visibility = View.GONE
                warning.visibility = View.GONE
                adapter.setListGrammar(grammars)
            } else {
                AppLogger.info("Default grammar if null")
                recyclerView.visibility = View.GONE
                updating.visibility = View.VISIBLE
                warning.visibility = View.VISIBLE
            }
        })
    }
}