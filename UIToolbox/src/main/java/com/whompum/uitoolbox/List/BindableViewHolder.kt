package com.whompum.uitoolbox.List

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Simple base class for creating ViewHolders, to enforce a wireframe for subclasses.
 *
 * @author Bryan A. Mills
 */
abstract class BindableViewHolder<T>(v: View) : RecyclerView.ViewHolder(v),
    Bindable<T>
