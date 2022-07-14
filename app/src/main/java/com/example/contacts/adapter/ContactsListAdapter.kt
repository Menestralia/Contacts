package com.example.contacts.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts.R
import com.example.contacts.model.UserContactItem
import com.example.contacts.databinding.ListItemContactBinding
import kotlin.math.min

class ContactsListAdapter(
    private val onContactChosen: (contact: UserContactItem) -> Unit,
) :
    RecyclerView.Adapter<ContactsListAdapter.ViewHolder>(){
    private val items = mutableListOf<UserContactItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_contact, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        items.getOrNull(position)?.let { contact ->
            holder.bind(contact)
            holder.itemView.setOnClickListener { onContactChosen(contact) }
        }
    }

    fun addItems(index: Int, newItems: List<UserContactItem>) {
        if (newItems.isNotEmpty()) {
            val actualIndex = min(items.size, index)
            items.addAll(actualIndex, newItems)
            notifyItemRangeInserted(actualIndex, newItems.size)
        }
    }

    open fun clear() {
        val oldItemsCount = items.size
        items.clear()
        notifyItemRangeRemoved(0, oldItemsCount)
    }

    fun getItems() = items

    override fun getItemCount() = items.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ListItemContactBinding.bind(view)

        fun bind(contact: UserContactItem) {
            binding.phoneNumber.text = contact.phone
            binding.name.text = contact.name
            binding.height.text = contact.height.toString()
        }
    }
}