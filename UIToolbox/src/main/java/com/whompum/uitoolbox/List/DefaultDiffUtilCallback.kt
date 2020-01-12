package com.whompum.uitoolbox.List

import androidx.recyclerview.widget.DiffUtil

/**
 * Standard utility class for Comparing different models in a [RecyclerView.Adapter] using
 * a [DiffUtil.Callback] class. This class simply commpares two items from equal positions in the list
 * using equals().
 * TODO Setup Comparison contracts
 */
class DefaultDiffUtilCallback<T>(val oldData: ArrayList<T>, val newData: ArrayList<T>): DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) = true
    override fun getOldListSize() = oldData.size
    override fun getNewListSize() = newData.size
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = false
}