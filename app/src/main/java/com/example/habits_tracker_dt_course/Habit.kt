package com.example.habits_tracker_dt_course

import com.example.habits_tracker_dt_course.constants.HabitPriority
import com.example.habits_tracker_dt_course.constants.HabitType
import java.io.Serializable

data class Habit(val title: String, val description: String, val priority: HabitPriority,
                 val habitType: HabitType, val repetitionCount: Int, val frequency: Int) : Serializable {
}