package com.whompum.main.HabitDashboard.HabitList.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.whompum.main.HabitDashboard.HabitList.HabitListContract
import com.whompum.main.HabitDashboard.HabitList.View.List.HabitListAdapter
import com.whompum.main.HabitDashboard.HabitList.View.List.HabitListItem
import com.whompum.main.R
import com.whompum.models.Habit.HabitPriority
import com.whompum.uitoolbox.List.EmptyViewRecyclerView
import com.whompum.uitoolbox.List.HeaderListItem
import timber.log.Timber

/**
 * Fragment class that exposes a list of habits via a [RecylerView]
 *
 * @author Bryan A. Mills
 * @date December 21st, 2019
 */
//TODO Launch material bottom sheet when filtering clicked to change filter
//TODO Setup MVP Architecture Here
//TODO Setup Data management from Presenter
//TODO Create a "New Habit" Screen and navigate to it when FAB clicked.
//TODO Setup bottom material sheet when a habit is clicked, to take further action.
//TODO Setup ability to change ordering of Headers in the list (Save to preferences)
//TODO Create Headers out of the dataset returned from the presenter
class HabitListFragment: Fragment(), HabitListContract.View {

    lateinit var habitListAdapter: HabitListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        habitListAdapter =
            HabitListAdapter()
    }

    @Suppress("HasPlatformType")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.layout_fragment_habit_list, container, false).apply {
        val placeholder: View = this.findViewById(R.id.placeholder)

        this.findViewById<EmptyViewRecyclerView>(R.id.recyclerview).apply {
            adapter = habitListAdapter
            placeholderView = placeholder
            this.setOnClickListener()
        }

    }

    override fun setData(data: List<HabitListItem>) {
    }

}