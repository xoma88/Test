package com.example.test.DataBase

import androidx.paging.DataSource
import androidx.room.*
import com.example.test.Model.Post.Message

@Dao
interface MyDao {
    @Insert
    fun insertAll(message : List<Message>)

    @Query("select * from message")
    fun getAllMessage() : DataSource.Factory<Int, Message>
}