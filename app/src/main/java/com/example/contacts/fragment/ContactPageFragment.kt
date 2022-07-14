package com.example.contacts.fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.contacts.R
import com.example.contacts.model.UserContactItem
import com.example.contacts.databinding.ContactPageBinding
import com.example.contacts.viewmodel.ContactPageViewModel

class ContactPageFragment(contactItem: UserContactItem) : PageFragment(R.layout.contact_page) {

    private lateinit var binding: ContactPageBinding
    private val viewModel: ContactPageViewModel = ContactPageViewModel(contactItem)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val contactItem = viewModel.getContactItem()
        binding = ContactPageBinding.inflate(inflater, container, false)
        binding.name.text = contactItem.name
        binding.phone.text = contactItem.phone
        binding.biography.text = contactItem.biography
        binding.temperament.text = contactItem.temperament
        binding.educationPeriodStart.text = viewModel.formatDate(contactItem.educationPeriodStart)
        binding.educationPeriodEnd.text = viewModel.formatDate(contactItem.educationPeriodEnd)
        binding.phone.setOnClickListener {
            context?.let { context ->
                viewModel.onCallPhoneNumber(context, contactItem.phone)
            }
        }
        binding.backBtn.setOnClickListener {
            popBackStack()
        }
        activity?.actionBar?.hide()
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity).supportActionBar?.title = ""
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                popBackStack()
            }
        })
        return binding.root
    }

    private fun popBackStack() {
        activity?.supportFragmentManager?.popBackStack()
    }

    companion object {
        val TAG = ContactPageFragment::class.java.simpleName
    }
}