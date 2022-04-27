package com.example.habits_tracker_dt_course.store


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.habits_tracker_dt_course.Habit
import com.example.habits_tracker_dt_course.HabitConverter

@Database(entities = [Habit::class], version = AppDatabase.VERSION)
@TypeConverters(HabitConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun habitsDao(): HabitsDao

    companion object {
        private const val DB_NAME = "habits.db"
        const val VERSION: Int = 1

        private var INSTANCE: AppDatabase? = null

        fun getHabitsDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room
                        .databaseBuilder(
                            context,
                            AppDatabase::class.java,
                            DB_NAME
                        )
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE!!
        }
    }

}
