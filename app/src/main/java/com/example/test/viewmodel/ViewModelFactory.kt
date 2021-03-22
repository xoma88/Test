package com.example.test.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.test.Model.MyRepository

class ViewModelFactory(private val repository: MyRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MessageViewModel::class.java)){
            return MessageViewModel(repository) as T
        }
        throw IllegalAccessException("Unknown class name")
    }
}