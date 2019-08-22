package com.whompum.commonui

import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ListViewHolder<T>(v: View): RecyclerView.ViewHolder(v), Bindable<T> {
    override fun bind(data: T) {
    }
}