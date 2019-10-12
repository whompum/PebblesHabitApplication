package com.whompum.uitoolbox.List

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * [RecyclerView] data observer that swaps between the main view and the RecyclerView itself
 */
class AdapterObserver(val viewOne: View, val viewTwo: RecyclerView) : RecyclerView.AdapterDataObserver() {

    // TODO Add logic to swap between two views
    override fun onChanged() {}
}
