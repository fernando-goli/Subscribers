package com.fgomes.subscribers.ui.subscriber

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.fgomes.subscribers.R
import com.fgomes.subscribers.data.db.AppDatabase
import com.fgomes.subscribers.data.db.dao.SubscriberDao
import com.fgomes.subscribers.databinding.SubscriberFragmentBinding
import com.fgomes.subscribers.extension.hideKeyboard
import com.fgomes.subscribers.repository.DatabaseDataSource
import com.fgomes.subscribers.repository.SubscriberRepository
import com.google.android.material.snackbar.Snackbar

class SubscriberFragment : Fragment() {
//R.layout.subscriber_fragment

    private var _binding: SubscriberFragmentBinding? = null
    private val binding:  SubscriberFragmentBinding get() = _binding!!

    private val viewModel: SubscriberViewModel by viewModels {
        object : ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val subscriberDao: SubscriberDao =
                    AppDatabase.getDatabase(requireContext()).subscriberDao()

                val repository : SubscriberRepository = DatabaseDataSource(subscriberDao)
                return SubscriberViewModel(repository) as T
            }
        }
    }

    private val args: SubscriberFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SubscriberFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            args.subscriber?.let{ subscriberEntity ->
                binding.btSubscriber.text = getString(R.string.subscriber_bt_update)
                binding.tieName.setText(subscriberEntity.name)
                binding.tieEmail.setText(subscriberEntity.email)

                binding.btDeleteSubscriber.visibility = View.VISIBLE
            }

            observeEvents()
            setListeners()
    }

    private fun observeEvents() {
        viewModel.subscriberStateEventData.observe(viewLifecycleOwner) { subscriberState ->
            when (subscriberState) {
                is SubscriberViewModel.SubscriberState.Inserted,
                is SubscriberViewModel.SubscriberState.Deleted,
                is SubscriberViewModel.SubscriberState.Updated -> {
                    clearFields()
                    hideKeyboard()
                    requireView().requestFocus()
                    findNavController().popBackStack()
                }


            }
        }

        viewModel.messageEventData.observe(viewLifecycleOwner) { stringResId ->
            Snackbar.make(requireView(), stringResId, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun clearFields() {
        binding.tieName.text?.clear()
        binding.tieEmail.text?.clear()
    }

    private fun hideKeyboard() {
        val parentActivity = requireActivity()
        if (parentActivity is AppCompatActivity) {
            parentActivity.hideKeyboard()
        }
    }

    private fun setListeners() {
        binding.btSubscriber.setOnClickListener {
            val name = binding.tieName.text.toString()
            val email = binding.tieEmail.text.toString()
            if (name.isNotBlank() && email.isNotBlank()) {
                viewModel.addOrUpdateSubscriber(name, email, args.subscriber?.id ?: 0)
            }

        }

        binding.btDeleteSubscriber.setOnClickListener {
            viewModel.deleteSubscriber(args.subscriber?.id ?: 0)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
