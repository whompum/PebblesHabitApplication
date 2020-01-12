package com.whompum.main.HabitDashboard.HabitList.View.List.ViewHolder

import android.view.View
import android.widget.TextView
import com.whompum.main.HabitDashboard.HabitList.View.List.HabitListItem
import com.whompum.main.R
import com.whompum.uitoolbox.List.BindableViewHolder
import com.whompum.uitoolbox.List.HeaderListItem
import timber.log.Timber

//TODO Setup onclick to toggle children visibility
class HeaderViewHolder(v: View): BindableViewHolder<HeaderListItem<HabitListItem>>(v) {
    override fun bind(data: HeaderListItem<HabitListItem>) {
        itemView.findViewById<TextView>(R.id.header_title)
            .text = data.headerTitle
    }
}