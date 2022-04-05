package com.example.habits_tracker_dt_course.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.habits_tracker_dt_course.R
import com.example.habits_tracker_dt_course.adapters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_habits_main.*

class HabitsMainFragment : Fragment(R.layout.fragment_habits_main) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewPager2.adapter = ViewPagerAdapter(this)
        val tabNames =
            listOf(getString(R.string.view_pager_useful), getString(R.string.view_pager_harmful))

        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            tab.text = tabNames[position]
        }.attach()

        addNewHabitButton.setOnClickListener {
            val action = HabitsMainFragmentDirections.actionFragmentHabitsMainToAddEditFragment(getString(R.string.add_habit))
            findNavController().navigate(action)
        }

        super.onViewCreated(view, savedInstanceState)
    }

}