package com.example.habits_tracker_dt_course.store

import com.example.habits_tracker_dt_course.Habit
import com.example.habits_tracker_dt_course.constants.HabitPriority
import com.example.habits_tracker_dt_course.constants.HabitType

object HabitsStorage {

    val habitsList: MutableList<Habit> = mutableListOf(
        Habit(
            title = "Попарам",
            description = "Если у вас есть какие то интересные предложения, обращайтесь! Студия Web-Boss всегда готова решить любую задачу.",
            priority = HabitPriority.High,
            habitType = HabitType.Harmful,
            repetitionCount = 3,
            frequency = 4
        ),
        Habit(
            title = "Голуби",
            description = "Товарищи! сложившаяся структура организации представляет собой интересный эксперимент проверки направлений прогрессивного развития.",
            priority = HabitPriority.Low,
            habitType = HabitType.Useful,
            repetitionCount = 3,
            frequency = 4
        ),
        Habit(
            title = "Такратас",
            description = "Повседневная практика показывает, что реализация намеченных плановых заданий в значительной степени обуславливает создание модели развития.",
            priority = HabitPriority.High,
            habitType = HabitType.Harmful,
            repetitionCount = 3,
            frequency = 4
        ),
        Habit(
            title = "Jopa",
            description = "Не следует, однако забывать, что дальнейшее развитие различных форм деятельности способствует подготовки и реализации форм развития.",
            priority = HabitPriority.Low,
            habitType = HabitType.Useful,
            repetitionCount = 3,
            frequency = 4
        ),
        Habit(
            title = "Гловач Лена",
            description = "Таким образом новая модель организационной деятельности способствует подготовки и реализации систем массового участия.",
            priority = HabitPriority.High,
            habitType = HabitType.Harmful,
            repetitionCount = 3,
            frequency = 4
        ),
        Habit(
            title = "Hui",
            description = "Значимость этих проблем настолько очевидна, что консультация с широким активом играет важную роль в формировании новых предложений.",
            priority = HabitPriority.Low,
            habitType = HabitType.Useful,
            repetitionCount = 3,
            frequency = 4
        )


    )

    fun addHabit(newHabit: Habit) {
        habitsList.add(newHabit)
    }

    fun replaceHabit(oldHabit: Habit, newHabit: Habit) {
        val index = habitsList.indexOf(oldHabit)
        habitsList[index] = newHabit
    }
}