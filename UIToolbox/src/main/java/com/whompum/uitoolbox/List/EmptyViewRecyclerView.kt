package com.whompum.uitoolbox.List

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.whompum.uitoolbox.Utils.toggleViewVisibility

//TODO TDD class until complete development of features
class EmptyViewRecyclerView(ctx: Context, set: AttributeSet?, style: Int) :
    RecyclerView(ctx, set, style) {

    var placeholderView: View? = null
    set(value) {
        field = value
        field?.let {
            toggleEmptyView(it)
        }
    }

    private val placeHolderDataObserver = PlaceHolderDataObserver()

    constructor(ctx: Context, set: AttributeSet?): this(ctx, set, 0)

    constructor(ctx: Context): this(ctx, null)

    private fun toggleEmptyView(view: View) {
        toggleViewVisibility(view) {
            adapter == null || adapter!!.itemCount == 0
        }
    }

    override fun setAdapter(adapter: Adapter<*>?) {
        getAdapter()?.unregisterAdapterDataObserver(placeHolderDataObserver)
        super.setAdapter(adapter)
        adapter?.registerAdapterDataObserver(placeHolderDataObserver)
        adapter?.notifyDataSetChanged()
    }

    private inner class PlaceHolderDataObserver: AdapterDataObserver() {
        override fun onChanged() {
            toggle()
        }
        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            toggle()
        }

        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            toggle()
        }

        private fun toggle() {
            placeholderView?.let {
                toggleEmptyView(it)
            }
        }
    }

}