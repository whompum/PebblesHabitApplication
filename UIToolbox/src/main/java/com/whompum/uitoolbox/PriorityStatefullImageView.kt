package com.whompum.uitoolbox

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import com.whompum.models.Habit.HabitPriority
import com.whompum.models.Habit.HabitPriority.*

/**
 * An [ImageView] that manages custom states
 * to change its background according to a [HabitPriority] state.
 * TODO Setup XML based state setting.
 */
class PriorityStatefullImageView(ctx: Context, attrs: AttributeSet) : ImageView(ctx, attrs) {

    /**
     * Default value holding containing the current
     * [R.attr] state
     */
    var priorityStateInternal: IntArray = IntArray(1)

    override fun onCreateDrawableState(extraSpace: Int): IntArray {
        val space =
            super.onCreateDrawableState(extraSpace + R.styleable.habit_priority_state.size)

        @Suppress("USELESS_ELVIS")
        View.mergeDrawableStates(
            space, priorityStateInternal ?: intArrayOf(priorityMapping.getValue(DEFAULT_STATE))
        )

        return space
    }

    fun setPriority(priority: HabitPriority) {
        priorityStateInternal[0] = priorityMapping.getValue(priority)
        refreshDrawableState()
    }

    companion object {

        val DEFAULT_STATE = STATE_HIGH_UNCOMPLETED

        /**
         * Priority mappings between external [HabitPriority]
         * and attrs declared at [R.styleable.habit_priority_state]
         */
        private val priorityMapping = mapOf(
            STATE_NORMAL_UNCOMPLETED to R.attr.state_priority_normal_uncompleted,
            STATE_NORMAL_COMPLETED to R.attr.state_priority_normal_completed,
            STATE_HIGH_UNCOMPLETED to R.attr.state_priority_high_uncompleted,
            STATE_HIGH_COMPLETED to R.attr.state_priority_high_completed
        )
    }

}