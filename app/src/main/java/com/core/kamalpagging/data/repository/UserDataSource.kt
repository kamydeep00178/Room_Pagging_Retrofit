package com.core.kamalpagging.data.repository

import android.content.Context
import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.core.kamalpagging.data.model.User
import com.core.kamalpagging.data.api.APIClinet
import com.core.kamalpagging.data.model.Data
import com.core.kamalpagging.data.db.AppDataBase
import com.core.kamalpagging.data.db.UserDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserDataSource (private val context: Context) : PageKeyedDataSource<Int, User>() {
    private val userDao: UserDao = AppDataBase.getDatabase(context).userDao()
    var dataList: List<User>? = null


   // val subscribers = userDao.getAllTopic()

    suspend fun getAllEmployeesLiveData(): List<User> {
        return userDao.getAllTopic()
    }


    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, User>
    ) {
       CoroutineScope(Dispatchers.IO).launch {
           dataList= getAllEmployeesLiveData()
           getUsers(callback)
       }



    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, User>) {
        val previousPageNo = if (params.key > 1) params.key - 1 else 0
        getMoreUsers(params.key, previousPageNo, callback)

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, User>) {
        val nextPageNo = params.key + 1
        getMoreUsers(params.key, nextPageNo, callback)
    }
    private  fun  getUsers(callback: LoadInitialCallback<Int, User>) {

        if (dataList?.size!! > 1) {
            callback.onResult(dataList!!, null, 2)
        } else {


            APIClinet.apiService.getUsers(1).enqueue(object : Callback<Data> {
                override fun onFailure(call: Call<Data>, t: Throwable) {


                }

                override fun onResponse(call: Call<Data>, response: Response<Data>) {

                    val userResponse = response.body()

                    userResponse?.let {
                        callback.onResult(it.data, null, 2)
                        CoroutineScope(Dispatchers.IO).launch {
                             userDao.insertAll(it.data)

                        }

                    }

                }


            })
         }


    }
    private fun getMoreUsers(pageNo: Int, previousOrNextPageNo: Int, callback: LoadCallback<Int, User>) {

        Log.e("TAG", "getMoreUsers: "+pageNo)

        APIClinet.apiService.getUsers(pageNo).enqueue(object : Callback<Data> {
            override fun onFailure(call: Call<Data>, t: Throwable) {
                //   Utility.hideProgressBar()
                Log.e("TAG", "onFailure: " + t.message)

            }

            override fun onResponse(call: Call<Data>, response: Response<Data>) {
                val userResponse = response.body()
                Log.e("TAG", "onResponse: " + userResponse)
                if (dataList?.size != userResponse?.total) {
                    Log.e("if TAG", "onResponse: " )
                    userResponse?.let {
                        callback.onResult(it.data, previousOrNextPageNo)
                        CoroutineScope(Dispatchers.IO).launch {
                            userDao.insertAll(it.data)

                        }
                    }
                }
            }

        })

    }
}