/*
 * Copyright (c) Created 2019 by cyber-gh, Soltan Gheorghe
 * Email: soltangh.work@gmail.com
 * All copyrights reserved
 */

package dev.skyit.utilities.generic.recycableList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dev.skyit.utilities.base.BaseViewModel
import kotlinx.coroutines.launch

abstract class RecyclerListViewModel<Element> : BaseViewModel() {

    private val _recyclerListState = MutableLiveData<RecyclerListState<Element>>()
    val recyclerListState: LiveData<out RecyclerListState<Element>> = _recyclerListState

    abstract val initialData: List<Element>

    protected abstract suspend fun getData(): List<Element>

    fun requestMoreData() {
        viewModelScope.launch {
            val data = getData()
            _recyclerListState.postValue(RecyclerListState(data))
        }
    }
}

