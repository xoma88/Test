package com.example.test.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.test.DataBase.MyDatabase
import com.example.test.ItemBoundaryCallback
import com.example.test.Model.Post.Message
import com.example.test.Network.ApiCommon

class MessageViewModel(application: Application) : ViewModel() {
    val db = MyDatabase.getInstance(application)
    val myDao = db.myDao()
    val apiService = ApiCommon.service

    val message: LiveData<PagedList<Message>>
    val boundaryCallback = ItemBoundaryCallback(apiService, myDao)

    init {
        val factory: DataSource.Factory<Int, Message> = MyDatabase.getInstance(application).myDao().getAllMessage()

        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .setPrefetchDistance(5)
            .setEnablePlaceholders(false)
            .build()

        message = LivePagedListBuilder(factory,config)
            .setBoundaryCallback(boundaryCallback)
            .build()
    }

    fun getAllMessage() = message
}