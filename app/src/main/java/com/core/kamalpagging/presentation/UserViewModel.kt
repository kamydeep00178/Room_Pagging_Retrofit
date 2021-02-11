package com.core.kamalpagging.presentation

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.core.kamalpagging.data.model.User
import com.core.kamalpagging.data.repository.UserDataSource
import com.core.kamalpagging.data.repository.UserDataSourceFactory

class UserViewModel(private val context: Context,private val repository: UserDataSource) : ViewModel() {
    private var listUsers: LiveData<PagedList<User>> = MutableLiveData<PagedList<User>>()
    private var mutableLiveData = MutableLiveData<UserDataSource>()

    private var mutableLiveData1 = MutableLiveData<User>()

 //   val subscribers = repository.subscribers


    override fun onCleared() {
        super.onCleared()
    }

    init {

        val factory: UserDataSourceFactory by lazy {
            UserDataSourceFactory(context)
        }
        mutableLiveData = factory.mutableLiveData

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(10)
            .build()

        listUsers = LivePagedListBuilder(factory, config)
            .build()

    }

    fun getData(): LiveData<PagedList<User>> {
        return listUsers
    }

}
