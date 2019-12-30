package com.whompum.uitoolbox

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import com.whompum.models.Habit.HabitPriority
import com.whompum.models.Habit.HabitPriority.STATE_NORMAL_UNCOMPLETED

/**
 * An [ImageView] that manages custom states
 * to change its background according to a [HabitPriority] state.
 * TODO Setup XML based state setting.
 */
class PriorityStatefullImageView(val ctx: Context, val attrs: AttributeSet): ImageView(ctx, attrs) {

    var priority: HabitPriority = STATE_NORMAL_UNCOMPLETED
    set (p) {
        field = p
        //Notify Listeners
        refreshDrawableState()
    }

    override fun onCreateDrawableState(extraSpace: Int) = mergeDrawableStates(
            super.onCreateDrawableState(extraSpace), R.styleable.habit_priority_state
    )

}