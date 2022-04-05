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
        0 -> HabitsListFragment.newInstance { habit -> habit.habitType == HabitType.Useful }
        else -> HabitsListFragment.newInstance{ habit -> habit.habitType == HabitType.Harmful }
    }
}