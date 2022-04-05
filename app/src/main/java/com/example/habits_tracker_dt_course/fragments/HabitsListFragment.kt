package com.example.habits_tracker_dt_course.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.habits_tracker_dt_course.Habit
import com.example.habits_tracker_dt_course.R
import com.example.habits_tracker_dt_course.adapters.HabitListAdapter
import com.example.habits_tracker_dt_course.store.HabitsStorage.habitsLiveData
import kotlinx.android.synthetic.main.fragment_habits_list.*

class HabitsListFragment(private val isAvailableHabit: (Habit) -> Boolean) :
    Fragment(R.layout.fragment_habits_list) {

    private val adapter: HabitListAdapter by lazy {
        HabitListAdapter()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        recyclerHabits.adapter = adapter

        habitsLiveData.observe(viewLifecycleOwner) { habitsList ->
            adapter.changeListOfHabits((habitsList.filter(isAvailableHabit).toMutableList()))
        }

        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance(isAvailableHabit: (Habit) -> Boolean): HabitsListFragment =
            HabitsListFragment(isAvailableHabit)
    }

}