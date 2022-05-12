
package com.example.habits_tracker_dt_course.store
import androidx.room.*
import com.example.habits_tracker_dt_course.model.Habit

@Dao
interface HabitsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHabit(habit: Habit)

    @Query("SELECT * from Habit ORDER BY uid DESC")
    suspend fun selectAllHabits(): List<Habit>

    @Delete
    suspend fun deleteHabit(habit: Habit)

    @Query("DELETE FROM Habit")
    suspend fun deleteAllHabits()

    @Update
    suspend fun updateHabit(habit: Habit)

}