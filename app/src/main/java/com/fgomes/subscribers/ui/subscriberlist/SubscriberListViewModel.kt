package com.fgomes.subscribers.ui.subscriberlist

import androidx.lifecycle.ViewModel
import com.fgomes.subscribers.repository.SubscriberRepository

class SubscriberListViewModel (
    private val repository: SubscriberRepository
        ) : ViewModel() {

            val allSubscriberEvent = repository.getAllSubscriber()

}