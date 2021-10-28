package com.c1ctech.jetpackpagingexp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.c1ctech.jetpackpagingexp.data.MyApi
import com.c1ctech.jetpackpagingexp.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var passengersViewModel: PassengersViewModel
    lateinit var passengersAdapter: PassengersAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setupViewModel()
        setupList()
        setupView()
    }

    private fun setupViewModel() {
        val factory = PassengersViewModelFactory(MyApi())
        passengersViewModel = ViewModelProvider(this, factory).get(PassengersViewModel::class.java)
    }

    private fun setupList() {
        passengersAdapter = PassengersAdapter()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = passengersAdapter.withLoadStateHeaderAndFooter(
                header = PassengersLoadStateAdapter { passengersAdapter.retry() },
                footer = PassengersLoadStateAdapter { passengersAdapter.retry() }
            )
            setHasFixedSize(true)
        }

    }

    private fun setupView() {
        lifecycleScope.launch {
            passengersViewModel.passengers.collectLatest { pagedData ->
                passengersAdapter.submitData(pagedData)
            }
        }
    }
}