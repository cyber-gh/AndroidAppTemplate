/*
 * Copyright (c) Created 2019 by cyber-gh, Soltan Gheorghe
 * Email: soltangh.work@gmail.com
 * All copyrights reserved
 */

package dev.skyit.utilities.generic.recycableList

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import dev.skyit.utilities.R

abstract class ElementView<Element>(context: Context) : BaseView(context) {
    var customCallback: ResultCallback<Any>? = null

    abstract fun setData(data: Element)
}

abstract class BaseView : ConstraintLayout {
    abstract val layoutId: Int

    private fun inflateView() {
        View.inflate(context, layoutId, this)
    }


    constructor(context: Context) : super(context) {
        inflateView()
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        inflateView()
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyle: Int) : super(
        context,
        attributeSet,
        defStyle
    ) {
        inflateView()
    }
}

open class EmptyView<Element>(context: Context)
    : ElementView<Element>(context) {
    override fun setData(data: Element) {
    }

    override val layoutId: Int
        get() = R.layout.empty_list_element_view

}