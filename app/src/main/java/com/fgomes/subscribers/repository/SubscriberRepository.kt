package com.fgomes.subscribers.repository

import androidx.lifecycle.LiveData
import com.fgomes.subscribers.data.db.entity.SubscriberEntity

interface SubscriberRepository {

    suspend fun insertSubscriber (name: String, email: String): Long
    suspend fun updateSubscriber (id: Long, name: String, email: String)
    suspend fun deleteSubscriber (id: Long)
    suspend fun deleteAllSubscriber ()
    fun getAllSubscriber (): LiveData<List<SubscriberEntity>>
}