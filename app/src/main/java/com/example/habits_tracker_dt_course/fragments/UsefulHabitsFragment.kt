package com.example.habits_tracker_dt_course.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.habits_tracker_dt_course.R
import com.example.habits_tracker_dt_course.adapters.HabitListAdapter
import com.example.habits_tracker_dt_course.constants.HabitType
import com.example.habits_tracker_dt_course.store.HabitsStorage
import kotlinx.android.synthetic.main.fragment_useful_habits.*

class UsefulHabitsFragment : Fragment(R.layout.fragment_useful_habits) {

    private val adapter: HabitListAdapter by lazy {
        HabitListAdapter()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        recyclerUsefulHabits.adapter = adapter

        HabitsStorage.habitsLiveData.observe(viewLifecycleOwner) { habitsList ->
            adapter.changeListOfHabits((habitsList.filter { it.habitType == HabitType.Useful}.toMutableList()))
        }

        super.onViewCreated(view, savedInstanceState)
    }

    private fun Int.toBoolean(): Boolean = this == 1

    companion object {
        fun newInstance(): UsefulHabitsFragment = UsefulHabitsFragment()
    }

}