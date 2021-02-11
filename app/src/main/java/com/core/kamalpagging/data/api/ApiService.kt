package com.core.kamalpagging.data.api

import com.core.kamalpagging.data.model.Data
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    fun getUsers(@Query("page") page:Int): Call<Data>
}