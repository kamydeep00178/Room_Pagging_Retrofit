package com.core.kamalpagging.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.core.kamalpagging.R
import com.core.kamalpagging.data.model.User
import com.core.kamalpagging.databinding.ActivityMainBinding
import com.core.kamalpagging.data.repository.UserDataSource

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter : UserAdapter
    val list: MutableList<User> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
        initRecyclerView()


        val repository = UserDataSource(applicationContext)


        val userViewModel = ViewModelProvider(this, UserViewModelFactory(applicationContext,repository)).get(UserViewModel::class.java)
        userViewModel.getData().observe(this, object : Observer<PagedList<User>> {
            override fun onChanged(t: PagedList<User>?) {
                adapter.submitList(t)
            }
        })





    }
    private fun initRecyclerView() {
        binding.recyclerMain.layoutManager = LinearLayoutManager(this@MainActivity)
        adapter = UserAdapter(this)
        binding.recyclerMain.adapter = adapter

    }
}