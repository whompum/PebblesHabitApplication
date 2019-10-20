package com.whompum.uitoolbox.List

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.VisibleForTesting
import androidx.recyclerview.widget.RecyclerView

/**
 * A simple base class for easing RecyclerView list creation
 *
 * @author Bryan A. Mills
 * @date August 8th, 2019
 */
abstract class ListAdapter<T> : RecyclerView.Adapter<BindableViewHolder<T>>() {

    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    protected var dataset = ArrayList<T>(0)

    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    protected var inflater: LayoutInflater? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindableViewHolder<T> {
        inflater?.let { inflater = LayoutInflater.from(parent.context) }
        return getHolder(inflater!!.inflate(getLayout(viewType), parent, false))
    }

    override fun getItemCount() = dataset.size

    override fun onBindViewHolder(holder: BindableViewHolder<T>, position: Int) {
        holder.bind(
            dataset[position]
        )
    }

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
    abstract fun getHolder(v: View): BindableViewHolder<T>
}
