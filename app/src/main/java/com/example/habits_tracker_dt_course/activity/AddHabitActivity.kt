package com.example.habits_tracker_dt_course.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.habits_tracker_dt_course.*
import com.example.habits_tracker_dt_course.constants.HabitPriority
import com.example.habits_tracker_dt_course.constants.HabitType
import com.example.habits_tracker_dt_course.constants.IntentCodes
import com.example.habits_tracker_dt_course.databinding.ActivityAddHabitBinding

class AddHabitActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddHabitBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddHabitBinding.inflate(layoutInflater)
        setContentView(binding.root)


        if (intent.extras != null){
            initEditing()
        }

        binding.createHabitButton.setOnClickListener {
            val habit = getHabitFromFormData()
            if (habit != null){
                println(habit)
                var newIntent = Intent(this, HabitsActivity::class.java).run {
                    putExtra(IntentCodes.NEW_HABIT.name, habit)
                }

                if (intent.extras != null) {
                    val position = intent.getIntExtra(IntentCodes.HABIT_POSITION.name, 0)
                    newIntent = Intent(this, HabitsActivity::class.java).run {
                        putExtra(IntentCodes.HABIT_POSITION.name, position)
                        putExtra(IntentCodes.CHANGED_HABIT.name, habit)
                    }
                }
                setResult(Activity.RESULT_OK, newIntent)
                finish()
            }
            else {
                Toast.makeText(applicationContext, "All fields must be filled in", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initEditing() {
        setTitle(R.string.edit_habit)

        val habitToEdit = intent.getSerializableExtra(IntentCodes.HABIT_FOR_CHANGE.name) as Habit

        binding.title.setText(habitToEdit.title)
        binding.habitDescription.setText((habitToEdit.description))

        when(habitToEdit.priority) {
            HabitPriority.Low -> binding.habitPriorities.setSelection(1)
            HabitPriority.Normal -> binding.habitPriorities.setSelection(0)
            HabitPriority.High -> binding.habitPriorities.setSelection(2)
        }

        when(habitToEdit.habitType){
            HabitType.Useful -> binding.usefulRadio.isChecked = true
            HabitType.Harmful -> binding.harmfulRadio.isChecked = true
        }

        binding.habitFrequency.setText(habitToEdit.frequency.toString())
        binding.repetitionCount.setText(habitToEdit.repetitionCount.toString())

        binding.createHabitButton.setText(R.string.edit_button_label)
    }

    private fun getHabitFromFormData(): Habit? {
        val habitType = when (binding.habitTypes.checkedRadioButtonId) {
            binding.usefulRadio.id -> HabitType.Useful
            binding.harmfulRadio.id -> HabitType.Harmful
            else -> null
        }
        val priority = HabitPriority.valueOf(binding.habitPriorities.selectedItem.toString())
        val habitFrequency = binding.habitFrequency.text.toString().toIntOrNull()
        val repetitionCount = binding.repetitionCount.text.toString().toIntOrNull()
        val title = binding.title.text.toString()
        val description = binding.habitDescription.text.toString()

        if (habitType != null
            && title.isNotEmpty()
            && description.isNotEmpty()
            && repetitionCount != null
            && habitFrequency != null
        ) {
            return Habit(
                title,
                description,
                priority, habitType, repetitionCount, habitFrequency
            )
        }
        return null
    }
}