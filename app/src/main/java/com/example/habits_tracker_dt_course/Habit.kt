package com.example.habits_tracker_dt_course

import android.os.Parcelable
import com.example.habits_tracker_dt_course.constants.HabitPriority
import com.example.habits_tracker_dt_course.constants.HabitType
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class Habit(
    val title: String,
    val description: String,
    val priority: HabitPriority,
    val habitType: HabitType,
    val repetitionCount: Int,
    val frequency: Int
) : Parcelable