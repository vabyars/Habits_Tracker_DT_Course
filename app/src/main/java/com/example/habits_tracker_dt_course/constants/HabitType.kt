package com.example.habits_tracker_dt_course.constants

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class HabitType(val value: Int) : Parcelable {
    Useful(0),
    Harmful(1)
}