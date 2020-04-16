/*
 * Copyright (c) Created 2019 by cyber-gh, Soltan Gheorghe
 * Email: soltangh.work@gmail.com
 * All copyrights reserved
 */

package dev.skyit.utilities.generic.recycableList

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.skyit.utilities.base.BaseFragment

abstract class RecyclerListFragment<Element>(
    @LayoutRes layoutId: Int, private val recyclerViewId: Int

) : BaseFragment(layoutId) {
    protected abstract val vModel: RecyclerListViewModel<Element>

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var listAdapter: ListAdapter<Element>
    private lateinit var recyclerView: RecyclerView
    protected abstract val elementViewFactory: ElementViewFactory<Element>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    protected open fun initialize() {
        recyclerView = view?.findViewById(recyclerViewId) ?: return
        linearLayoutManager = LinearLayoutManager(context ?: return)
        recyclerView.layoutManager = linearLayoutManager
        listAdapter = ListAdapter(vModel.initialData, elementViewFactory)
        recyclerView.adapter = listAdapter
        vModel.recyclerListState.observe(viewLifecycleOwner, Observer {
            listAdapter.updateData(it.data)
        })
        vModel.requestMoreData()
    }

}