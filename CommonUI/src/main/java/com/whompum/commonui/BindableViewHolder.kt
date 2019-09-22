package com.whompum.commonui

import android.view.View
import androidx.recyclerview.widget.RecyclerView

class BindableViewHolder<T>(v: View): RecyclerView.ViewHolder(v), Bindable<T> {
    override fun bind(data: T) {
    }
}