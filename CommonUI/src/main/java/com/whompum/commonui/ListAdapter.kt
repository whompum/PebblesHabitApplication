package com.whompum.commonui

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
abstract class ListAdapter<T>: RecyclerView.Adapter<ListViewHolder<T>>() {

    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    protected var dataset = ArrayList<T>(0)

    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    protected var inflater: LayoutInflater? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder<T> {
        inflater?.let { inflater = LayoutInflater.from(parent.context) }
        return getHolder(inflater!!.inflate(getLayout(), parent, false))
    }

    override fun getItemCount() = dataset.size

    override fun onBindViewHolder(holder: ListViewHolder<T>, position: Int) {
        holder.bind(
            dataset[position]
        )
    }

    @LayoutRes
    abstract fun getLayout(): Int

    abstract fun getHolder(v: View): ListViewHolder<T>
}