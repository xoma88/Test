package com.example.test.Network

object ApiCommon {
    const val BASE_URL = "https://gorest.co.in/"
    val service: ApiService
    get() = ApiClient.getClient(BASE_URL).create(ApiService::class.java)
}