package com.example.test.Model

import androidx.paging.DataSource
import com.example.test.DataBase.MyDao
import com.example.test.Model.Post.Message

class MyRepository(myDao: MyDao) {
    var dao = myDao
    private var page = 1
    private var pages = 0

    fun getMessageDb(): DataSource.Factory<Int, Message> {
        return dao.getAllMessage()
    }
    fun insertMessageDb(message: List<Message>){
        Thread {
            dao.insertAll(message)
        }.start()
    }
    fun setPageApi(page: Int){
        this.page = page
    }
    fun getPageApi(): Int {
        return this.page
    }
    fun setPagesApi(page: Int){
        this.pages = page
    }
    fun getPagesApi(): Int {
        return this.pages
    }
}