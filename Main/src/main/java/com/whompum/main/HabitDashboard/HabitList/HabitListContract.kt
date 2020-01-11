package com.whompum.main.HabitDashboard.HabitList

import com.whompum.main.HabitDashboard.HabitList.View.List.HabitListItem

//TODO Add model to represent 'Pebbles'
//TODO Use non view based model to represent HabitListItem
interface HabitListContract {
    interface View {
        fun setData(data: List<HabitListItem>)
    }
}