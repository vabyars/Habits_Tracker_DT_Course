
package com.example.habits_tracker_dt_course.store
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.habits_tracker_dt_course.Habit

@Dao
interface HabitsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHabit(habit: Habit)

    @Query("SELECT * from Habit ORDER BY id DESC")
    suspend fun selectAllHabits(): List<Habit>

    @Update
    suspend fun updateHabit(habit: Habit)

}