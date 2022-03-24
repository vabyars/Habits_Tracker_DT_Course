package com.example.habits_tracker_dt_course.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.habits_tracker_dt_course.R
import com.example.habits_tracker_dt_course.adapters.HabitListAdapter
import com.example.habits_tracker_dt_course.constants.HabitType
import com.example.habits_tracker_dt_course.store.HabitsStorage.Companion.habitsLiveData
import kotlinx.android.synthetic.main.fragment_harmful_habits.*

class HarmfulHabitsFragment : Fragment(R.layout.fragment_harmful_habits) {

    private val adapter: HabitListAdapter by lazy {
        HabitListAdapter()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        recyclerHarmfulHabits.adapter = adapter

        habitsLiveData.observe(viewLifecycleOwner) { habitsList ->
            adapter.changeListOfHabits((habitsList.filter { it.habitType == HabitType.Harmful}.toMutableList()))
        }

        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance(): HarmfulHabitsFragment = HarmfulHabitsFragment()
    }

}