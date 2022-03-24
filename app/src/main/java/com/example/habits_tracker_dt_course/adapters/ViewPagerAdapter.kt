package com.example.habits_tracker_dt_course.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.habits_tracker_dt_course.fragments.HarmfulHabitsFragment
import com.example.habits_tracker_dt_course.fragments.UsefulHabitsFragment

class ViewPagerAdapter(
    fragment: Fragment
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> UsefulHabitsFragment.newInstance()
        else -> HarmfulHabitsFragment.newInstance()
    }
}