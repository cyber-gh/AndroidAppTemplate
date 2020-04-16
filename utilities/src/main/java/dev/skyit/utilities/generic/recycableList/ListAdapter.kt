/*
 * Copyright (c) Created 2019 by cyber-gh, Soltan Gheorghe
 * Email: soltangh.work@gmail.com
 * All copyrights reserved
 */

package dev.skyit.utilities.generic.recycableList

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

open class ListAdapter<Element>(
    private var data: List<Element>,
    private val elementViewFactory: ElementViewFactory<Element>,
    private val resultCallback: ResultCallback<Element>? = null,
    private val customCallback: ResultCallback<Any>? = null
) : RecyclerView.Adapter<ListAdapter.ElementHolder<Element, ElementView<Element> > > () {

    open class BaseElementHolder(view: View) : RecyclerView.ViewHolder(view)

    open class ElementHolder<Element, VType : ElementView<Element>>(val view: VType) :
        BaseElementHolder(view) {

        fun setData(
            data: Element,
            resultCallback: ResultCallback<Element>? = null,
            customCallback: ResultCallback<Any>? = null
        ) {
            view.setData(data)

            view.customCallback = customCallback
            view.setOnClickListener {
                resultCallback?.onReady(data)
            }
        }
    }

    class EmptyViewHolder<Element, VType : ElementView<Element>>(view: VType) : ElementHolder<Element, VType>(view)

    override fun getItemViewType(position: Int): Int {
        return if (data.isNotEmpty()) 0
        else 1
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ElementHolder<Element, ElementView<Element>> {
        return if (viewType == 0) {
            val inflatedView = elementViewFactory.generateView()
            ElementHolder(inflatedView)
        } else {
            val inflatedView = elementViewFactory.generateEmptyView()
            EmptyViewHolder(inflatedView)
        }
    }

    override fun getItemCount(): Int {
        return if (data.isNotEmpty()) data.size else 1
    }


    override fun onBindViewHolder(
        holder: ElementHolder<Element, ElementView<Element>>,
        position: Int
    ) {
        if (holder.itemViewType == 0) {
            val data = data[position]
            holder.setData(data, resultCallback, customCallback)
        }
    }

    fun updateData(newData: List<Element>?) {
        if (newData == null) return
        this.data = newData
        this.notifyDataSetChanged()
    }


}

abstract class ElementViewFactory<Element>(protected val context: Context) {
    abstract fun generateView(): ElementView<Element>
    open fun generateEmptyView() : ElementView<Element> {
        return EmptyView(context)
    }
}