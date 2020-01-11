package com.whompum.main.HabitDashboard.HabitList.View.List.ViewHolder

import android.view.View
import android.widget.TextView
import com.whompum.main.HabitDashboard.HabitList.View.List.HabitListItem
import com.whompum.main.R
import com.whompum.uitoolbox.List.BindableViewHolder
import com.whompum.uitoolbox.PriorityStatefullImageView
import timber.log.Timber

//TODO Unit Test Class!
class HabitViewHolder(v: View): BindableViewHolder<HabitListItem>(v) {
    override fun bind(data: HabitListItem) {
        Timber.d("bind")

        itemView.findViewById<TextView>(R.id.habit_title)
            .text = data.title

        itemView.findViewById<PriorityStatefullImageView>(R.id.priority_image)
            .priority = data.state

        itemView.findViewById<TextView>(R.id.habit_time)
            .text = data.days
    }
}