package com.example.habits_tracker_dt_course.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.habits_tracker_dt_course.R
import com.example.habits_tracker_dt_course.adapters.ViewPagerAdapter
import com.example.habits_tracker_dt_course.viewModels.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.bottom_sheet_main_fragment.*
import kotlinx.android.synthetic.main.fragment_habits_main.*

class HabitsMainFragment : Fragment(R.layout.fragment_habits_main) {

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_fragment_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.findAndSort) {
            bottomSheetMainFragment?.let{
                val behavior = BottomSheetBehavior.from(bottomSheetMainFragment)
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        return true
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewPager2.adapter = ViewPagerAdapter(this)
        val tabNames =
            listOf(getString(R.string.view_pager_useful), getString(R.string.view_pager_harmful))

        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            tab.text = tabNames[position]
        }.attach()

        addNewHabitButton.setOnClickListener {
            val action = HabitsMainFragmentDirections.actionFragmentHabitsMainToAddEditFragment(getString(R.string.add_habit))
            findNavController().navigate(action)
        }

        val behavior = BottomSheetBehavior.from(bottomSheetMainFragment)
        behavior.state = BottomSheetBehavior.STATE_HIDDEN

        filterFind.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(text: Editable?) {
                if (text != null) {
                    viewModel.sortHabits(text.toString())
                } else {
                    viewModel.cleanHabitsFilter()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })

        super.onViewCreated(view, savedInstanceState)
    }



}