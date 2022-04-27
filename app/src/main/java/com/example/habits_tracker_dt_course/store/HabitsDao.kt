
package com.example.habits_tracker_dt_course.store
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.habits_tracker_dt_course.Habit

@Dao
interface HabitsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHabit(habit: Habit)

    @Query("SELECT * from Habit ORDER BY id DESC")
    fun selectAllHabits(): List<Habit>

    @Query("SELECT * from Habit WHERE habitType = 1 ORDER BY id DESC")
    fun selectBadHabits(): List<Habit>

    @Query("SELECT * FROM Habit WHERE habitType = 0 ORDER BY id DESC")
    fun selectGoodHabits(): List<Habit>

    @Delete
    fun deleteHabit(habit: Habit)

    @Update
    fun updateHabit(habit: Habit)

}