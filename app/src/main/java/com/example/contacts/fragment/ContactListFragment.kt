package com.example.contacts.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contacts.app.App
import com.example.contacts.viewmodel.list.ContactsListViewModelFactory
import com.example.contacts.adapter.ContactsListAdapter
import com.example.contacts.R
import com.example.contacts.state.StateListFragment
import com.example.contacts.model.UserContactItem
import com.example.contacts.databinding.ContactListPageBinding
import com.example.contacts.viewmodel.list.ContactsListViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class ContactListFragment : Fragment(), FragmentManager.OnBackStackChangedListener {

    @Inject
    lateinit var viewModelFactory: ContactsListViewModelFactory
    private val viewModel: ContactsListViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[ContactsListViewModel::class.java]
    }

    private lateinit var binding: ContactListPageBinding

    private var adapter = ContactsListAdapter { contact ->
        viewModel.onContactSelected(contact)
    }
    private val layoutManager = LinearLayoutManager(
        context,
        LinearLayoutManager.VERTICAL,
        false
    )


    fun EditText.textChanges(): Flow<CharSequence?> {
        return callbackFlow {
            val listener = object : TextWatcher {
                override fun afterTextChanged(s: Editable?) = Unit
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) = Unit

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    trySend(s)
                }
            }
            addTextChangedListener(listener)
            awaitClose { removeTextChangedListener(listener) }
        }.onStart { emit(text) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (context?.applicationContext as App).appComponent.inject(this)
        binding = ContactListPageBinding.inflate(inflater, container, false)
        binding.listContacts.adapter = adapter
        binding.listContacts.layoutManager = layoutManager
        binding.listContacts.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        );
        binding.searchEditText.textChanges().debounce(500)
            .flatMapLatest {
                viewModel.filterContacts(it.toString())
            }
            .launchIn(lifecycleScope)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.selectedContact.observe(viewLifecycleOwner) {
            onContactSelected(it)
        }
        viewModel.stateList.observe(viewLifecycleOwner) {
            onStateChanged(it)
        }
        viewModel.setDefaultState()
    }

    override fun onDestroy() {
        super.onDestroy()
        activity?.finish()
    }

    private fun onContactSelected(contact: UserContactItem?) {
        contact?.let {
            openContactCard(it)
        }
    }

    private fun onStateChanged(state: StateListFragment) {
        when (state) {
            StateListFragment.ERROR -> showError(viewModel.errorLoading)
            StateListFragment.PROCESSING -> showProgressBar()
            else -> updateRows(state)
        }
    }

    private fun openContactCard(contact: UserContactItem) {
        activity?.supportFragmentManager?.beginTransaction()?.add(
            R.id.container,
            ContactPageFragment(contact),
            ContactPageFragment.TAG
        )
            ?.addToBackStack(TAG)
            ?.commit()
    }

    private fun updateRows(state: StateListFragment) {
        hideProgressBar()
        var contactsList = mutableListOf<UserContactItem>()
        if (state == StateListFragment.DEFAULT) {
            viewModel.getContactList()?.let {
                contactsList.addAll(it)
            }
        } else if (state == StateListFragment.FILTER_ITEMS) {
            viewModel.getFilterList()?.let {
                contactsList.addAll(it)
            }
        }
        Log.e(TAG, "updateRows: ${contactsList.size}")
        (binding.listContacts.adapter as ContactsListAdapter).clear()
        (binding.listContacts.adapter as ContactsListAdapter)
            .addItems(0, contactsList)
    }

    override fun onResume() {
        super.onResume()

        if (viewModel.isTimerExpired())
            viewModel.loadContacts()
    }

    private fun showError(errorMessage: String?) {
        errorMessage?.let {
            Snackbar.make(binding.listLayout, it, Snackbar.LENGTH_LONG)
                .show()
        }
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }

    override fun onBackStackChanged() {
        activity?.supportFragmentManager?.backStackEntryCount?.let {
            if (it < 1) {
                activity?.actionBar?.setDisplayHomeAsUpEnabled(false);
            } else
                activity?.actionBar?.setDisplayHomeAsUpEnabled(true);
        }
    }

    companion object {
        val TAG = ContactListFragment::class.java.simpleName
    }

}