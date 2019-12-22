package com.whompum.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * Fragment class that exposes a list of habits via a [RecylerView]
 *
 * @author Bryan A. Mills
 * @date December 21st, 2019
 */
class HabitListFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.layout_fragment_habit_list, container, false)

}