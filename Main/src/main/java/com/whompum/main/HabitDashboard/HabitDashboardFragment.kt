package com.whompum.main.HabitDashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.whompum.main.HabitDashboard.Dashboard.DashboardFragment
import com.whompum.main.HabitDashboard.HabitList.View.HabitListFragment
import com.whompum.main.R
import com.whompum.uitoolbox.Utils.getOrCreateFragment

/**
 * Entry point fragment that contains the [HabitListFragment]
 * and [DashboardFragment]
 */
class HabitDashboardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.layout_habit_dashboard, container, false).apply {

        val viewPager: ViewPager = this.findViewById(R.id.viewpager)

        viewPager.adapter = HabitDashboardPageAdapter()

        this.findViewById<TabLayout>(R.id.habit_dashboard_tabs).setupWithViewPager(viewPager)
    }

    private inner class HabitDashboardPageAdapter :
        FragmentStatePagerAdapter(fragmentManager!!, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        override fun getItem(position: Int): Fragment {
            if (position == 0)
                return getOrCreateFragment("TAG", fragmentManager!!, HabitListFragment::class.java)

            return getOrCreateFragment("TAG", fragmentManager!!, DashboardFragment::class.java)
        }

        override fun getCount() = 2

        override fun getPageTitle(position: Int): CharSequence? {
            if (position == 0)
                return getString(R.string.habits_tab_title)

            return getString(R.string.dashboard_tab_title)
        }
    }

}