package com.example.habits_tracker_dt_course.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.habits_tracker_dt_course.R
import com.example.habits_tracker_dt_course.databinding.ActivityHabitsBinding
import kotlinx.android.synthetic.main.activity_habits.*


class HabitsActivity : AppCompatActivity() {

    private val navController: NavController by lazy {
        findNavController(R.id.habitsActivityFragment)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_habits)

        setSupportActionBar(toolbarHabitsActivity)
        val appBarConfiguration = AppBarConfiguration(navController.graph, navigationDrawerLayout)

        toolbarHabitsActivity.setupWithNavController(navController, appBarConfiguration)

        navigationView.setupWithNavController(navController)
        if (savedInstanceState == null) {
            navController.setGraph(R.navigation.nav_graph)
        }
    }

    override fun onBackPressed() {
        if (navigationDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            navigationDrawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}