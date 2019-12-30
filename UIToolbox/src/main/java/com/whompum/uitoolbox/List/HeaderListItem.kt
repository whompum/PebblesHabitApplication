package com.whompum.uitoolbox.List

import androidx.annotation.VisibleForTesting

/**
 * Generic base class for a [RecyclerView] that requires a header
 */
class HeaderListItem<T>(val headerTitle: String): ListItem {

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val items = ArrayList<T>(0)

    fun getCount() = items.size

    companion object {
        const val VIEW_TYPE = 0xabbdf
    }

}