package com.example.habits_tracker_dt_course.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.habits_tracker_dt_course.Habit
import com.example.habits_tracker_dt_course.store.HabitsStorage

class MainViewModel : ViewModel() {


    private val currentHabitsLiveData: MutableLiveData<MutableList<Habit>> = MutableLiveData()
    val currentHabits: LiveData<MutableList<Habit>> get() = currentHabitsLiveData

    init {
        currentHabitsLiveData.value = HabitsStorage.habitsList
    }

    fun addHabit(newHabit: Habit) {
        cleanHabitsFilter()
        HabitsStorage.addHabit(newHabit)
    }

    fun replaceHabit(oldHabit: Habit, newHabit: Habit) {
        cleanHabitsFilter()
        HabitsStorage.replaceHabit(oldHabit, newHabit)
    }

    fun sortHabits(text: String) { //Поиск по привычкам
        if (text.isNotEmpty()) {
            val sortedHabits = HabitsStorage.habitsList.filter {
                it.title.contains(text, ignoreCase = true)
            }
            currentHabitsLiveData.value = sortedHabits.toMutableList()
        } else {
            cleanHabitsFilter()
        }
    }

    fun cleanHabitsFilter() {
        currentHabitsLiveData.value = HabitsStorage.habitsList
    }

}