@file:Suppress("UNCHECKED_CAST")

package com.whompum.main.HabitDashboard.HabitList.View.List

import android.view.View
import com.whompum.main.HabitDashboard.HabitList.View.List.ViewHolder.HabitViewHolder
import com.whompum.main.HabitDashboard.HabitList.View.List.ViewHolder.HeaderViewHolder
import com.whompum.main.R
import com.whompum.uitoolbox.List.BindableViewHolder
import com.whompum.uitoolbox.List.HeaderListItem
import com.whompum.uitoolbox.List.ListAdapter
import com.whompum.uitoolbox.List.ListItem

//TODO Come up with a good algorithm to override swapDataset and add all header items as well as the headers into the main data set (merge)
class HabitListAdapter : ListAdapter<ListItem>() {

    override fun getLayout(viewType: Int) =
        if (viewType == HeaderListItem.VIEW_TYPE) R.layout.header_list_item else R.layout.habit_list_item

    override fun getHolder(v: View, viewType: Int) =
        if (viewType == HeaderListItem.VIEW_TYPE) HeaderViewHolder(v) as BindableViewHolder<ListItem>
        else HabitViewHolder(v) as BindableViewHolder<ListItem>

    override fun getItemViewType(position: Int): Int {
        if (dataset[position] is HeaderListItem<*>)
            return HeaderListItem.VIEW_TYPE

        return 1
    }

}