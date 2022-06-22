package com.example.elllo_english.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.elllo_english.ui.fragment.GrammarFragment
import com.example.elllo_english.ui.fragment.ScriptFragment
import com.example.elllo_english.utils.AppData

class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return AppData.FRAGMENT_COUNT
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            AppData.SCRIPT -> return ScriptFragment()
            AppData.GRAMMAR -> return GrammarFragment()
            else -> return ScriptFragment()
        }
    }
}