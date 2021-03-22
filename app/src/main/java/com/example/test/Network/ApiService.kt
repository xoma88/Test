package com.example.test.Network

import com.example.test.Model.Post.Post
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("public-api/posts")
    fun getMessageList(@Query("page") page : Int): Call<Post>
}