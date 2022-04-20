package com.example.habits_tracker_dt_course.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.habits_tracker_dt_course.Habit
import com.example.habits_tracker_dt_course.R
import com.example.habits_tracker_dt_course.adapters.HabitListAdapter
import com.example.habits_tracker_dt_course.constants.HabitType
import com.example.habits_tracker_dt_course.viewModels.MainViewModel
import kotlinx.android.synthetic.main.fragment_habits_list.*

class HabitsListFragment() :
    Fragment(R.layout.fragment_habits_list) {

    private val adapter: HabitListAdapter by lazy {
        HabitListAdapter()
    }

    private val viewModel: MainViewModel by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        recyclerHabits.adapter = adapter

        val habitType = arguments?.getParcelable<HabitType>(FRAGMENT_TYPE)

        viewModel.currentHabits.observe(viewLifecycleOwner) { habitsList ->
            adapter.changeListOfHabits(habitsList.filter { it.habitType == habitType}.toMutableList())
        }

        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        private const val FRAGMENT_TYPE = "fragment_type"
        fun newInstance(type: HabitType): HabitsListFragment {


            return HabitsListFragment().also {
                it.arguments = Bundle().apply { putParcelable(FRAGMENT_TYPE, type) }
            }
        }

    }

}