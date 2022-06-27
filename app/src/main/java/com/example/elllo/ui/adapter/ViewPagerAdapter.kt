package com.example.elllo.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.elllo.ui.fragment.GrammarFragment
import com.example.elllo.ui.fragment.ScriptFragment
import com.example.elllo.utils.AppData

class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return AppData.FRAGMENT_COUNT
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            AppData.SCRIPT -> ScriptFragment()
            AppData.GRAMMAR -> GrammarFragment()
            else -> ScriptFragment()
        }
    }
}