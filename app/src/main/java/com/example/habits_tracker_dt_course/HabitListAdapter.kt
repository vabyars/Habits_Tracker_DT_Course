package com.example.habits_tracker_dt_course

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.habits_tracker_dt_course.constants.HabitPriority
import com.example.habits_tracker_dt_course.constants.HabitType
import kotlinx.android.synthetic.main.habit_list_item.view.*


class HabitListAdapter(
    val onClickListItem: (Habit, Int) -> Unit
) :
    RecyclerView.Adapter<HabitListAdapter.ViewHolder>() {

    val habitsList: Array<Habit>
        get() = habits.toTypedArray()


    private var habits = mutableListOf<Habit>()


    inner class ViewHolder(private val itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(habit: Habit, position: Int) {
            itemView.habitTitleListItem.text = habit.title
            itemView.habitDescriptionListItem.text = habit.description
            itemView.habitPriorityText.text = habit.priority.toString()
            val priorityDot = when (habit.priority) {
                HabitPriority.Low -> R.drawable.low_priority_dot
                HabitPriority.Normal -> R.drawable.normal_priority_dot
                HabitPriority.High -> R.drawable.high_priority_dot
            }
            itemView.priorityDot.setImageResource(priorityDot)
            itemView.habitTypeText.text = habit.habitType.toString()

            val typeDot = when (habit.habitType) {
                HabitType.Useful -> R.drawable.useful_type_dot
                HabitType.Harmful -> R.drawable.harmful_type_dot
            }

            itemView.typeDot.setImageResource(typeDot)

            itemView.habitCountListItem.text = itemView.context.getString(R.string.count_card_label, habit.repetitionCount)
            itemView.habitFrequencyListItem.text = itemView.context.getString(R.string.frequency_card_label, habit.frequency)

            itemView.habitsListItem.setOnClickListener {
                onClickListItem(habit, position)
            }
        }
    }

    fun addItem(habit: Habit) {
        habits.add(habit)
        notifyItemInserted(itemCount - 1)
    }

    fun changeItem(habit: Habit, position: Int) {
        habits[position] = habit
        notifyItemChanged(position)
    }

    fun restoreItems(newHabits: Array<Habit>) {
        habits = newHabits.toMutableList()
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.habit_list_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(habits[position], position)
    }

    override fun getItemCount(): Int = habits.size
}