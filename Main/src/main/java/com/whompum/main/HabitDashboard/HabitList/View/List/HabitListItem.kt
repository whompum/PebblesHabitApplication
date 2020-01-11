package com.whompum.main.HabitDashboard.HabitList.View.List

import com.whompum.models.Habit.HabitPriority
import com.whompum.uitoolbox.List.ListItem

/**
 * Simple Habit UI model that houses data for the [HabitViewHolder]
 * TODO Override equals / hashcode
 */
data class HabitListItem(val title: String, val state: HabitPriority, val days: String): ListItem