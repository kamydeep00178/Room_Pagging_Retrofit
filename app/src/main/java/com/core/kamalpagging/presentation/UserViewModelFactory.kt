package com.core.kamalpagging.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.core.kamalpagging.data.repository.UserDataSource


class UserViewModelFactory(private val context: Context,private val repository: UserDataSource): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UserViewModel(context, repository) as T
    }
}