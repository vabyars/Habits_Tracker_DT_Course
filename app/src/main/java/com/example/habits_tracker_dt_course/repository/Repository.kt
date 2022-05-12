package com.example.habits_tracker_dt_course.repository

import com.example.habits_tracker_dt_course.model.Habit
import com.example.habits_tracker_dt_course.api.ApiService
import com.example.habits_tracker_dt_course.model.ServerHabit
import com.example.habits_tracker_dt_course.model.Uid
import com.example.habits_tracker_dt_course.store.HabitsDao
import retrofit2.Response

class Repository(
    private val habitsDao: HabitsDao,
    private val api: ApiService
) {
    suspend fun insertHabitIntoDB(habit: Habit) {
        habitsDao.insertHabit(habit)
    }

    suspend fun insertHabitsIntoDB(habits: List<Habit>) {
        habits.forEach {
            habitsDao.insertHabit(it)
        }
    }

    suspend fun getHabitsFromDB(): List<Habit> {
        return habitsDao.selectAllHabits()
    }

    suspend fun deleteHabitFromDB(habit: Habit) {
        habitsDao.deleteHabit(habit)
    }

    suspend fun deleteAllHabitsFromDB() {
        habitsDao.deleteAllHabits()
    }

    suspend fun updateHabitInDB(habit: Habit) {
        habitsDao.updateHabit(habit)
    }



    suspend fun insertHabitIntoApi(habit: ServerHabit): Response<Uid> =
        api.putHabit(habit)


    suspend fun getHabitsFromApi(): Response<List<ServerHabit>> =
        api.getHabits()



    suspend fun deleteHabitFromApi(uid: Uid): Response<Unit> =
        api.deleteHabit(uid)

}