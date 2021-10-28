package com.c1ctech.jetpackpagingexp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.c1ctech.jetpackpagingexp.data.MyApi

class PassengersViewModelFactory(
    private val api: MyApi
) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PassengersViewModel(api) as T
    }
}