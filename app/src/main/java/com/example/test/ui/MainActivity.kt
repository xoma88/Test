package com.example.test.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test.R
import com.example.test.viewmodel.MessageViewModel
import kotlinx.android.synthetic.main.main_content.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var messageAdapter = MessageAdapter()

        recyclerMessageList.layoutManager = LinearLayoutManager(this)

        var messageViewModel = ViewModelProvider(this).get(MessageViewModel::class.java)

        messageViewModel.getAllMessage().observe(this) {
            messageAdapter.submitList(it)
        }

        recyclerMessageList.adapter = messageAdapter

    }
}
