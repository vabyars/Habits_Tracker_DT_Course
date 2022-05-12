package com.example.habits_tracker_dt_course.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.habits_tracker_dt_course.model.Habit
import com.example.habits_tracker_dt_course.R
import com.example.habits_tracker_dt_course.constants.HabitPriority
import com.example.habits_tracker_dt_course.constants.HabitType
import com.example.habits_tracker_dt_course.viewModels.MainViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_add_habit.*
import java.util.*


class AddEditFragment : Fragment(R.layout.activity_add_habit) {

    private val args: AddEditFragmentArgs by navArgs()
    private val habitToEdit by lazy { args.habitToEdit  }
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(R.string.exit_title)
                .setMessage(R.string.exit_message)
                .setPositiveButton(R.string.yes) { _, _ ->
                    findNavController().popBackStack()
                }
                .setNegativeButton(R.string.no) { _, _ -> }
                .setCancelable(true)
                .show()
        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
        super.onViewCreated(view, savedInstanceState)
    }


    private fun getHabitFromFormData(): Habit? {
        val habitType = when (habitTypes.checkedRadioButtonId) {
            usefulRadio.id -> HabitType.Useful
            harmfulRadio.id -> HabitType.Harmful
            else -> null
        }
        val priority = HabitPriority.valueOf(habitPriorities.selectedItem.toString())
        val habitFrequency = habitFrequency.text.toString().toIntOrNull()
        val repetitionCount = repetitionCount.text.toString().toIntOrNull()
        val title = title.text.toString()
        val description = habitDescription.text.toString()

        if (habitType != null
            && title.isNotEmpty()
            && description.isNotEmpty()
            && repetitionCount != null
            && habitFrequency != null
        ) {
            return Habit(
                habitToEdit?.uid ?: UUID.randomUUID().toString(),
                title,
                description,
                priority, habitType, repetitionCount, habitFrequency
            )
        }
        return null
    }


    private fun init() {

        habitToEdit?.let{
            title.setText(it.title)
            habitDescription.setText(it.description)

            habitPriorities.setSelection(it.priority.value)

            when(it.habitType){
                HabitType.Useful -> usefulRadio.isChecked = true
                HabitType.Harmful -> harmfulRadio.isChecked = true
            }

            habitFrequency.setText(it.frequency.toString())
            repetitionCount.setText(it.repetitionCount.toString())

            createHabitButton.setText(R.string.edit_button_label)
        }

        createHabitButton.setOnClickListener {
            getHabitFromFormData()?.let { habit ->
                habitToEdit?.let {
                    mainViewModel.replaceHabit(habit)
                } ?: mainViewModel.addHabit(habit)
                findNavController().popBackStack()
            } ?: Toast.makeText(context, R.string.not_filled_data_toast_message, Toast.LENGTH_SHORT).show()
        }
    }
}

