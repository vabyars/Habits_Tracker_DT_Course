package com.example.habits_tracker_dt_course

import android.app.Application
import com.example.habits_tracker_dt_course.store.AppDatabase

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        print(applicationContext)
        AppDatabase.getHabitsDatabase(applicationContext)

    }
}