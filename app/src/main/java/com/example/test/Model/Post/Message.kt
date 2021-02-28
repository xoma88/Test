package com.example.test.Model.Post

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Message (
    @PrimaryKey
    var id: Int?,
    var user_id: Int? ,
    var title: String? ,
    var body: String? ,
    var created_at: String? ,
    var updated_at: String?
)
