package com.example.test

import androidx.paging.PagedList
import com.example.test.DataBase.MyDao
import com.example.test.Model.Post.Message
import com.example.test.Network.ApiService

class ItemBoundaryCallback(
    private val service: ApiService,
    private val myDao: MyDao
) : PagedList.BoundaryCallback<Message>() {
    var isLoading = false
    var page = 1

    override fun onZeroItemsLoaded(){

        if (isLoading) return
        isLoading = true
        val response = service.getNextMessageList(page).execute()
        if (response.isSuccessful){
            val data = response.body()?.data!!

            Thread {
                myDao.insertAll(data)
                isLoading = false
            }.start()
        }
    }

    override fun onItemAtEndLoaded(itemAtEnd: Message) {

        if (isLoading) return
        isLoading = true
            val response = service.getNextMessageList(page + 1).execute()
            if (response.isSuccessful){

                val data = response.body()?.data!!

                Thread {
                    myDao.insertAll(data)
                    isLoading = false
                }.start()
            }
    }
}