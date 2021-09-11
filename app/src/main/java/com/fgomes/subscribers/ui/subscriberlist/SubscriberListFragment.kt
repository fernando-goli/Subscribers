package com.fgomes.subscribers.ui.subscriberlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fgomes.subscribers.data.db.AppDatabase
import com.fgomes.subscribers.data.db.dao.SubscriberDao
import com.fgomes.subscribers.databinding.SubscriberListFragmentBinding
import com.fgomes.subscribers.repository.DatabaseDataSource
import com.fgomes.subscribers.repository.SubscriberRepository
import com.fgomes.subscribers.ui.subscriber.SubscriberViewModel

class SubscriberListFragment : Fragment() {

    private var _binding: SubscriberListFragmentBinding? = null
    private val binding:  SubscriberListFragmentBinding get() = _binding!!

    private val viewModel: SubscriberListViewModel by viewModels {
        object : ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val subscriberDao: SubscriberDao =
                    AppDatabase.getDatabase(requireContext()).subscriberDao()

                val repository : SubscriberRepository = DatabaseDataSource(subscriberDao)
                return SubscriberViewModel(repository) as T
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SubscriberListFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

}