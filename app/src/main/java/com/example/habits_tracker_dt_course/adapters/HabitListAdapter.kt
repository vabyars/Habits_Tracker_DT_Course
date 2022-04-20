package com.example.habits_tracker_dt_course.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.habits_tracker_dt_course.Habit
import com.example.habits_tracker_dt_course.R
import com.example.habits_tracker_dt_course.constants.HabitPriority
import com.example.habits_tracker_dt_course.constants.HabitType
import com.example.habits_tracker_dt_course.fragments.HabitsMainFragmentDirections
import kotlinx.android.synthetic.main.habit_list_item.view.*


class HabitListAdapter :
    RecyclerView.Adapter<HabitListAdapter.ViewHolder>() {

    private var habits = mutableListOf<Habit>()


    inner class ViewHolder(private val itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(habit: Habit) {

            itemView.habitsListItem.setOnClickListener {
                val action = HabitsMainFragmentDirections.actionFragmentHabitsMainToAddEditFragment(
                    itemView.context.resources.getString(R.string.edit_habit)
                )
                action.habitToEdit = habit

                findNavController(it).navigate(action)
            }

            itemView.habitTitleListItem.text = habit.title
            itemView.habitDescriptionListItem.text = habit.description
            itemView.habitPriorityText.text = habit.priority.toString()
            val priorityDot = when (habit.priority) {
                HabitPriority.Low -> R.drawable.low_priority_dot
                HabitPriority.Normal -> R.drawable.normal_priority_dot
                HabitPriority.High -> R.drawable.high_priority_dot
            }
            itemView.priorityDot.setImageResource(priorityDot)

            itemView.habitCountListItem.text = itemView.context.getString(R.string.count_card_label, habit.repetitionCount)
            itemView.habitFrequencyListItem.text = itemView.context.getString(R.string.frequency_card_label, habit.frequency)

        }
    }

    fun changeListOfHabits(newHabits: MutableList<Habit>) {
        habits = newHabits
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.habit_list_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(habits[position])
    }

    override fun getItemCount(): Int = habits.size
}