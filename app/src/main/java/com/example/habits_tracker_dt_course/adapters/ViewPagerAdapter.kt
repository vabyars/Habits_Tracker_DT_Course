package com.example.habits_tracker_dt_course.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.habits_tracker_dt_course.constants.HabitType
import com.example.habits_tracker_dt_course.fragments.HabitsListFragment

class ViewPagerAdapter(
    fragment: Fragment
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> HabitsListFragment.newInstance(HabitType.Useful)
        else -> HabitsListFragment.newInstance(HabitType.Harmful)
    }
}