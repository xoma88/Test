package com.example.test.Model

import androidx.paging.PagedList
import com.example.test.Model.Post.Message
import com.example.test.Model.Post.Post
import com.example.test.Network.ApiCommon
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemBoundaryCallback(
    private val repository: MyRepository
) : PagedList.BoundaryCallback<Message>() {
    var isLoading = false

    override fun onZeroItemsLoaded(){
        if (isLoading) return
        isLoading = true
        if (repository.getPageApi() != 1){
            repository.setPageApi(1)
        }
        ApiCommon.service.getMessageList(repository.getPageApi()).enqueue(object : Callback<Post> {
            override fun onFailure(call: Call<Post>, t: Throwable) {}
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful) {
                    repository.setPagesApi(response.body()?.meta?.pagination?.pages!!)
                    val data = response.body()?.data!!
                    repository.insertMessageDb(data)
                    isLoading = false
                }
            }
        })
        isLoading = false
    }

    override fun onItemAtEndLoaded(itemAtEnd: Message) {
        if (isLoading) return
        isLoading = true
        if (repository.getPageApi() < repository.getPagesApi()){
            repository.setPageApi(repository.getPageApi() + 1)
            ApiCommon.service.getMessageList(repository.getPageApi()).enqueue(object : Callback<Post> {
                override fun onFailure(call: Call<Post>, t: Throwable) {}
                override fun onResponse(call: Call<Post>, response: Response<Post>) {
                    if (response.isSuccessful) {
                        repository.setPagesApi(response.body()?.meta?.pagination?.pages!!)
                        val data = response.body()?.data!!
                        repository.insertMessageDb(data)
                    }
                }
            })
            isLoading = false
        }
        else {
            isLoading = false
            return
        }
    }
}