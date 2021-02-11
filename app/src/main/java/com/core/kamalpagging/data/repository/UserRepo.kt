package com.core.kamalpagging.data.repository

import com.core.kamalpagging.data.model.User

interface UserRepo {
    suspend fun getUserData() : List<User>

}