package com.fgomes.subscribers.ui.subscriberlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.fgomes.subscribers.R
import com.fgomes.subscribers.databinding.SubscriberListFragmentBinding
import com.fgomes.subscribers.extension.navigateWithAnimations
import org.koin.androidx.viewmodel.ext.android.viewModel

class SubscriberListFragment : Fragment() {

    private var _binding: SubscriberListFragmentBinding? = null
    private val binding:  SubscriberListFragmentBinding get() = _binding!!

    private val viewModel: SubscriberListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SubscriberListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observerViewModelEvents()
        configureViewListeners()

    }

    private fun observerViewModelEvents() {
        viewModel.allSubscriberEvent.observe(viewLifecycleOwner) { allSubs ->
            val subscriberListAdapter = SubscriberListAdapter(allSubs).apply {
                onItemClick = { subscriber ->
                    val directions = SubscriberListFragmentDirections
                        .actionSubscriberListFragmentToSubscriberFragment(subscriber)
                   findNavController().navigateWithAnimations(directions)
                }
            }

            with(binding.rvSubs) {
                setHasFixedSize(true)
                adapter = subscriberListAdapter
            }
        }
    }

    private fun configureViewListeners(){
        binding.fabAddSubscriber.setOnClickListener {
            findNavController().navigateWithAnimations(
                R.id.action_subscriberListFragment_to_subscriberFragment)
        }
    }

}