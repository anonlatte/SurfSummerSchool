package ru.surfstudio.summerschool.confirmation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.surfstudio.summerschool.confirmation.adapter.ContactsAdapter.ContactViewHolder
import ru.surfstudio.summerschool.data.ContactInfo
import ru.surfstudio.summerschool.databinding.ItemContactBinding

class ContactsAdapter : ListAdapter<ContactInfo, ContactViewHolder>(DiffUtilItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(
            ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class DiffUtilItemCallback : DiffUtil.ItemCallback<ContactInfo>() {
        override fun areItemsTheSame(oldItem: ContactInfo, newItem: ContactInfo): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: ContactInfo, newItem: ContactInfo): Boolean =
            oldItem == newItem
    }

    class ContactViewHolder(private val binding: ItemContactBinding) : ViewHolder(binding.root) {
        fun bind(contact: ContactInfo) = with(binding) {

        }
    }
}