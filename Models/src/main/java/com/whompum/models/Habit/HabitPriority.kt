package com.whompum.models.Habit

/**
 * Sealed state representing the current state a habit is existing in
 * in terms of both its priority and completed / uncompleted values.
 */
sealed class HabitPriority {
    /**
     * Sealed state representing a high-pri habit, that has been completed
     */
    object STATE_HIGH_COMPLETED: HabitPriority()

    /**
     * Sealed state representing a high-pri habit, that has not been completed
     */
    object STATE_HIGH_UNCOMPLETED: HabitPriority()

    /**
     * Sealed state representing a normal-pri habit, that has been completed
     */
    object STATE_NORMAL_COMPLETED: HabitPriority()

    /**
     * Sealed state representing a normal-pri habit, that has not been completed
     */
    object STATE_NORMAL_UNCOMPLETED: HabitPriority()
}