package com.example.habits_tracker_dt_course

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.habits_tracker_dt_course.constants.HabitPriority
import com.example.habits_tracker_dt_course.constants.HabitType
import kotlinx.android.parcel.Parcelize



@Parcelize
@Entity
@TypeConverters(HabitConverter::class)
data class Habit(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val title: String,
    val description: String,
    val priority: HabitPriority,
    val habitType: HabitType,
    val repetitionCount: Int,
    val frequency: Int
) : Parcelable

class HabitConverter {
    @TypeConverter
    fun fromHabitType(habitType: HabitType): Int {
        return habitType.value
    }

    @TypeConverter
    fun toHabitType(typeValue: Int): HabitType {
        return enumValues<HabitType>()[typeValue]
    }

    @TypeConverter
    fun fromPriority(priority: HabitPriority): Int {
        return priority.value
    }

    @TypeConverter
    fun toPriority(priorityValue: Int): HabitPriority {
        return enumValues<HabitPriority>()[priorityValue]
    }

}