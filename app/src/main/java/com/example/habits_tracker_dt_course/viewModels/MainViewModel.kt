package com.example.habits_tracker_dt_course.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.habits_tracker_dt_course.model.Habit
import com.example.habits_tracker_dt_course.api.ApiService
import com.example.habits_tracker_dt_course.model.HabitMapper
import com.example.habits_tracker_dt_course.model.ServerHabit
import com.example.habits_tracker_dt_course.repository.Repository
import com.example.habits_tracker_dt_course.store.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(application: Application) : AndroidViewModel(application) {


    private val currentHabitsLiveData: MutableLiveData<MutableList<Habit>> = MutableLiveData()
    val currentHabits: LiveData<MutableList<Habit>> get() = currentHabitsLiveData

    private val appDatabase = AppDatabase.getHabitsDatabase(application.applicationContext)
    private val repository = Repository(
        appDatabase.habitsDao(),
        ApiService.create().create(ApiService::class.java)
    )

    init {
        viewModelScope.launch(Dispatchers.IO){
            val habits = repository.getHabitsFromApi()

            habits.body()?.let { serverHabits ->
                repository.deleteAllHabitsFromDB()
                serverHabits.forEach { serverHabit ->
                    val habit = HabitMapper.serverHabitToHabit(serverHabit)
                    repository.insertHabitIntoDB(habit)
                }
            }

            currentHabitsLiveData.postValue(repository.getHabitsFromDB().toMutableList())
        }
    }


    fun addHabit(newHabit: Habit) {

        viewModelScope.launch(Dispatchers.IO){
            val serverHabit = ServerHabit(
                uid = null,
                title = newHabit.title,
                description = newHabit.description,
                priority = newHabit.priority.value,
                type = newHabit.habitType.value,
                count = newHabit.repetitionCount,
                frequency = newHabit.frequency,
                date = 0,
                doneDates = mutableListOf(),
                color = 0,
            )
            val habitUid = repository.insertHabitIntoApi(serverHabit).body()
            newHabit.uid = habitUid!!.uid
            repository.insertHabitIntoDB(newHabit)
            cleanHabitsFilter()
        }
    }

    fun replaceHabit(newHabit: Habit) {
        viewModelScope.launch(Dispatchers.IO){
            repository.insertHabitIntoApi(HabitMapper.habitToServerHabit(newHabit))
            repository.updateHabitInDB(newHabit)
            cleanHabitsFilter()
        }

    }

    fun deleteHabit(habitToDelete: Habit) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteHabitFromDB(habitToDelete)
            cleanHabitsFilter()
        }
    }

    fun sortHabits(text: String) { //Поиск по привычкам
        viewModelScope.launch(Dispatchers.IO) {
            if (text.isNotEmpty()) {
                val sortedHabits =  repository.getHabitsFromDB().filter {
                    it.title.contains(text, ignoreCase = true)
                }
                currentHabitsLiveData.postValue(sortedHabits.toMutableList())
            } else {
                cleanHabitsFilter()
            }
        }
    }

    fun cleanHabitsFilter() {
        viewModelScope.launch(Dispatchers.IO){
            currentHabitsLiveData.postValue(repository.getHabitsFromDB().toMutableList())
        }
    }

}