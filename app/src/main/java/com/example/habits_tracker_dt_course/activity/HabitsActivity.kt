package com.example.habits_tracker_dt_course.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.habits_tracker_dt_course.Habit
import com.example.habits_tracker_dt_course.HabitListAdapter
import com.example.habits_tracker_dt_course.constants.IntentCodes
import com.example.habits_tracker_dt_course.databinding.ActivityHabitsBinding


class HabitsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHabitsBinding


    private val adapter: HabitListAdapter by lazy {
        HabitListAdapter { habit, position ->

            val intent = Intent(this, AddHabitActivity::class.java).apply {
                putExtra(IntentCodes.HABIT_POSITION.name, position)
                putExtra(IntentCodes.HABIT_FOR_CHANGE.name, habit)
            }
            changeHabitLauncher.launch(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHabitsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.habitsList.adapter = adapter

        binding.addNewHabitButton.setOnClickListener {
            val intent = Intent(this, AddHabitActivity::class.java)
            addHabitLauncher.launch(intent)
        }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        outState.putSerializable(IntentCodes.HABITS.name, adapter.habitsList)

        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val habits =
            savedInstanceState.getSerializable(IntentCodes.HABITS.name) as Array<Habit>
        if (habits.isNotEmpty()) {
            adapter.restoreItems(habits)
        }
    }

    private val addHabitLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val newHabit = data?.getSerializableExtra(IntentCodes.NEW_HABIT.name) as Habit
                adapter.addItem(newHabit)
            }
        }

    private val changeHabitLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val habit = data?.getSerializableExtra(IntentCodes.CHANGED_HABIT.name) as Habit
                val position = data.getIntExtra(IntentCodes.HABIT_POSITION.name, 0)
                adapter.changeItem(habit, position)
            }
        }
}