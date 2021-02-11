package com.core.kamalpagging.data.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.core.kamalpagging.data.model.User

class UserDataSourceFactory(private val context: Context): DataSource.Factory<Int, User>() {
    val mutableLiveData = MutableLiveData<UserDataSource>()
    override fun create(): DataSource<Int, User> {
        val userDataSource = UserDataSource(context)
        mutableLiveData.postValue(userDataSource)
        return userDataSource
    }

}