package com.example.habits_tracker_dt_course.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.example.habits_tracker_dt_course.Habit
import com.example.habits_tracker_dt_course.store.AppDatabase

class MainViewModel(application: Application) : AndroidViewModel(application) {


    private val currentHabitsLiveData: MutableLiveData<MutableList<Habit>> = MutableLiveData()
    val currentHabits: LiveData<MutableList<Habit>> get() = currentHabitsLiveData

    //обернуть в LiveData запросы
    private val appDatabase = AppDatabase.getHabitsDatabase(application.applicationContext)
    private val habitsDao = appDatabase.habitsDao()

    init {
        currentHabitsLiveData.value = habitsDao.selectAllHabits().toMutableList()
    }


    fun addHabit(newHabit: Habit) {
        cleanHabitsFilter()
        habitsDao.insertHabit(newHabit)
    }

    fun replaceHabit(oldHabit: Habit, newHabit: Habit) {
        cleanHabitsFilter()
        habitsDao.updateHabit(newHabit)
    }

    fun sortHabits(text: String) { //Поиск по привычкам
        if (text.isNotEmpty()) {
            val sortedHabits = habitsDao.selectAllHabits().filter {
                it.title.contains(text, ignoreCase = true)
            }
            currentHabitsLiveData.value = sortedHabits.toMutableList()
        } else {
            cleanHabitsFilter()
        }
    }

    fun cleanHabitsFilter() {
        currentHabitsLiveData.value = habitsDao.selectAllHabits().toMutableList()
    }

}