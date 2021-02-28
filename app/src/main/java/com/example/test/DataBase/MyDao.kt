package com.example.test.DataBase

import androidx.paging.DataSource
import androidx.room.*
import com.example.test.Model.Post.Message

@Dao
interface MyDao {
    @Insert//(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(message : List<Message>)

    @Query("select * from message")
    fun getAllMessage() : DataSource.Factory<Int, Message>

    @Delete
    fun delete(message: Message)
}