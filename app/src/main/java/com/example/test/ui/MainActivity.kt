package com.example.test.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test.DataBase.MyDatabase
import com.example.test.Model.MyRepository
import com.example.test.R
import com.example.test.viewmodel.ViewModelFactory
import com.example.test.viewmodel.MessageViewModel
import kotlinx.android.synthetic.main.main_content.*

class MainActivity : AppCompatActivity() {
    lateinit var messageAdapter: MessageAdapter
    lateinit var messageViewModel: MessageViewModel
    lateinit var repository: MyRepository
    lateinit var myDb: MyDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myDb = MyDatabase.getInstance(this)
        clearTables(myDb)
        val myDao = myDb.myDao()

        repository = MyRepository(myDao)
        messageAdapter = MessageAdapter()

        recyclerMessageList.layoutManager = LinearLayoutManager(this)

        messageViewModel = ViewModelProvider(
            this,
            ViewModelFactory(repository)
        ).get(MessageViewModel::class.java)

        messageViewModel.getAllMessage().observe(this) {
            messageAdapter.submitList(it)
        }

        recyclerMessageList.adapter = messageAdapter
    }

    override fun onStop() {
        super.onStop()
        clearTables(myDb)
    }

    fun clearTables(db: MyDatabase){
        Thread{
            db.clearAllTables()
        }.start()
    }
}
