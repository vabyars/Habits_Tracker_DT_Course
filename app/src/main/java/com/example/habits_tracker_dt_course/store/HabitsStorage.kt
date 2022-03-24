package com.example.habits_tracker_dt_course.store

import androidx.lifecycle.MutableLiveData
import com.example.habits_tracker_dt_course.Habit

class HabitsStorage {
    companion object {

        var habitsLiveData: MutableLiveData<MutableList<Habit>> = MutableLiveData()

        init {
            habitsLiveData.value = mutableListOf()
        }

        fun addHabit(newHabit: Habit) {
            habitsLiveData.value!!.add(newHabit)
        }

        fun replaceHabit(oldHabit: Habit, newHabit: Habit) {
            habitsLiveData.value?.let{value ->
                val index = value.indexOf(oldHabit)
                value.removeAt(index)
                value.add(index, newHabit)
            }

        }

    }

}