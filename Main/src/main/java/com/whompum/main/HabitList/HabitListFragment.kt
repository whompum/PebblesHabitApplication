package com.whompum.main.HabitList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.whompum.main.R

/**
 * Fragment class that exposes a list of habits via a [RecylerView]
 *
 * @author Bryan A. Mills
 * @date December 21st, 2019
 */
//TODO Create a xml file for the filtering section.
//TODO Create an  XML File for the Header View
//TODO Setup MVP Architecture Here
//TODO Setup Data management from Presenter
//TODO Create a "New Habit" Screen and navigate to it when FAB clicked.
//TODO Setup bottom material sheet when a habit is clicked, to take further action.
//TODO Setup ability to change ordering of Headers in the list.
class HabitListFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.layout_fragment_habit_list, container, false)

}