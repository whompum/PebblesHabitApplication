package com.whompum.uitoolbox.List

import androidx.annotation.VisibleForTesting
import java.util.ArrayList

/**
 * Generic base class for a [RecyclerView] that requires a header
 */
class HeaderListItem<T: ListItem>(val headerTitle: String): ListItem {

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val items = ArrayList<T>()

    fun addItems(data: List<T>) {
      items.addAll(data)
    }

    fun getCount() = items.size

    companion object {
        const val VIEW_TYPE = 0xabbdf
    }

}