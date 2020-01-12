package com.whompum.uitoolbox.List

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.annotation.VisibleForTesting
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

/**
 * A simple base class for easing RecyclerView list creation
 *
 * @author Bryan A. Mills
 * @date August 8th, 2019
 * TODO Setup Async Coroutines for the DiffUtil
 * TODO Setup Swapping / Insertion / Deletion contracts (use an interface to represent these operations)
 */
abstract class ListAdapter<T> : RecyclerView.Adapter<BindableViewHolder<T>>() {

    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    protected var dataset = ArrayList<T>(0)

    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    protected var inflater: LayoutInflater? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindableViewHolder<T> {
        if (inflater == null)
            inflater = LayoutInflater.from(parent.context)

        return getHolder(inflater!!.inflate(getLayout(viewType), parent, false), viewType)
    }

    override fun getItemCount() = dataset.size

    override fun onBindViewHolder(holder: BindableViewHolder<T>, position: Int) {
        holder.bind(
            dataset[position]
        )
    }

    /**
     * Single client interface to add data to this adapters data set.
     */
    @CallSuper
    open fun swapDataset(newData: ArrayList<T>) {
        DiffUtil.calculateDiff(getDiffUtilCallback(newData)).dispatchUpdatesTo(this)
        this.dataset = newData
    }

    /**
     * Returns a [DiffUtil.Callback] implementation
     */
    fun getDiffUtilCallback(newData: ArrayList<T>): DiffUtil.Callback = DefaultDiffUtilCallback<T>(dataset, newData)

    /**
     * @param The view type for this layout
     * @return A layout id that will be associated with each item in the list.
     */
    @LayoutRes
    abstract fun getLayout(viewType: Int): Int

    /**
     * Overridden by a subclass to define its [RecyclerView.ViewHolder implementation]
     * @return A  subclass defined implementation of [BindableViewHolder]
     */
    abstract fun getHolder(v: View, viewType: Int): BindableViewHolder<T>
}
