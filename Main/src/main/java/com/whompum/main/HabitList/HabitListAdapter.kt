@file:Suppress("UNCHECKED_CAST")

package com.whompum.main.HabitList

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import com.whompum.uitoolbox.List.BindableViewHolder
import com.whompum.uitoolbox.List.HeaderListItem
import com.whompum.uitoolbox.List.ListAdapter
import com.whompum.uitoolbox.List.ListItem

class HabitListAdapter : ListAdapter<ListItem>() {

    override fun getLayout(viewType: Int) = if (viewType == HeaderListItem.VIEW_TYPE) -1 else 1

    override fun getHolder(v: View, viewType: Int) =
        if (viewType == HeaderListItem.VIEW_TYPE) HeaderViewHolder(v) as BindableViewHolder<ListItem>
        else HabitViewHolder(v) as BindableViewHolder<ListItem>
}