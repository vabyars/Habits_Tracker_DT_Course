package com.example.habits_tracker_dt_course.model

import com.example.habits_tracker_dt_course.constants.HabitPriority
import com.example.habits_tracker_dt_course.constants.HabitType
import java.time.LocalDateTime
import java.util.*

object HabitMapper {

    fun habitToServerHabit(habit : Habit) =
        ServerHabit(
            uid = habit.uid,
            title = habit.title,
            description = habit.description,
            priority = habit.priority.value,
            type = habit.habitType.value,
            count = habit.repetitionCount,
            frequency = habit.frequency,
            date = System.currentTimeMillis(),
            doneDates = mutableListOf(),
            color = 0,
        )

    fun serverHabitToHabit(habit : ServerHabit)=
        Habit(
            uid = habit.uid ?: UUID.randomUUID().toString(),
            title = habit.title,
            description = habit.description,
            priority = enumValues<HabitPriority>()[habit.priority],
            habitType = enumValues<HabitType>()[habit.type],
            repetitionCount = habit.count,
            frequency = habit.frequency,
        )

}