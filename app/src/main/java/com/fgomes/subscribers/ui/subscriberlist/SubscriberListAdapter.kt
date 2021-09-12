package com.fgomes.subscribers.ui.subscriberlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fgomes.subscribers.data.db.entity.SubscriberEntity
import com.fgomes.subscribers.databinding.SubscriberItemBinding

class SubscriberListAdapter (
    private val subscribers: List<SubscriberEntity>
        ): RecyclerView.Adapter<SubscriberListAdapter.SubscriberListViewHolder>() {

    var onItemClick: ( (entity: SubscriberEntity) -> Unit )? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubscriberListViewHolder {
        val itemBinding = SubscriberItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SubscriberListViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: SubscriberListViewHolder, position: Int) {
        holder.bindView(subscribers[position])
    }

    override fun getItemCount(): Int = subscribers.size

    inner class SubscriberListViewHolder(itemView: SubscriberItemBinding) : RecyclerView.ViewHolder(itemView.root){

        private val textViewSubscriberName:TextView = itemView.tvSubName
        private val textViewSubscriberEmail:TextView = itemView.tvSubEmail

        fun bindView(subscribers: SubscriberEntity ) {
            textViewSubscriberName.text = subscribers.name
            textViewSubscriberEmail.text = subscribers.email

            itemView.setOnClickListener {
                onItemClick?.invoke(subscribers)
            }
        }

    }
}