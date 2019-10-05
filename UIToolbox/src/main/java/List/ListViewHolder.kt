package com.whompum.UIToolbox.List

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Generic Base class for a [RecyclerView.Adapter]
 */
abstract class ListViewHolder<T>(v: View) : RecyclerView.ViewHolder(v), Bindable<T>
