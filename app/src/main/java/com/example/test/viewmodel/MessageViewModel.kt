package com.example.test.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.test.Model.ItemBoundaryCallback
import com.example.test.Model.Post.Message
import com.example.test.Model.MyRepository

class MessageViewModel(myRepository: MyRepository) : ViewModel() {

    val repository = myRepository
    val boundaryCallback = ItemBoundaryCallback(repository)
    val message: LiveData<PagedList<Message>>

    init {
        val factory: DataSource.Factory<Int, Message> = repository.getMessageDb()

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