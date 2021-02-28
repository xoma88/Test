package com.example.test.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.test.Model.Post.Message
import com.example.test.R
import kotlinx.android.synthetic.main.card_view.view.*

class MessageAdapter : PagedListAdapter<Message, MessageAdapter.MessageViewHolder>(MESSAGE_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view, parent, false)

        return MessageViewHolder(view)
    }

    override fun onBindViewHolder(holderMessage: MessageViewHolder, position: Int) {
        val message = getItem(position)

        message?.let { holderMessage.bind(it) }
    }

    class MessageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val title = view.titleText
        private val body = view.bodyText

        fun bind(message: Message){
            title.text = message.title
            body.text = message.body
        }
    }

    companion object {
        private val MESSAGE_COMPARATOR = object : DiffUtil.ItemCallback<Message>() {
            override fun areItemsTheSame(oldItem: Message, newItem: Message)
            = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Message, newItem: Message)
            = newItem == oldItem
        }
    }
}