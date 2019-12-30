package com.whompum.main.HabitList

import android.view.View
import android.widget.TextView
import com.whompum.main.R
import com.whompum.uitoolbox.List.BindableViewHolder
import com.whompum.uitoolbox.PriorityStatefullImageView

class HabitViewHolder(v: View): BindableViewHolder<HabitListItem>(v) {
    override fun bind(data: HabitListItem) {
        itemView.findViewById<TextView>(R.id.habit_title)
            .text = data.title

        itemView.findViewById<PriorityStatefullImageView>(R.id.priority_image)
            .priority = data.state

        itemView.findViewById<TextView>(R.id.habit_time)
            .text = data.days
    }
}