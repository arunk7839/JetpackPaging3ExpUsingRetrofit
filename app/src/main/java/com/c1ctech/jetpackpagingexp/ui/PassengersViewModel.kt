package com.c1ctech.jetpackpagingexp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.c1ctech.jetpackpagingexp.data.MyApi

class PassengersViewModel(
    private val api: MyApi
) : ViewModel() {
    val passengers =
        Pager(config = PagingConfig(pageSize = 10, prefetchDistance = 2), pagingSourceFactory = {
            PassengersDataSource(api)
        }).flow.cachedIn(viewModelScope)
}
