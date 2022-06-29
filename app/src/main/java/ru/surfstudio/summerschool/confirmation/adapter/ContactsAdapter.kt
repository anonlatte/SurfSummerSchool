package ru.surfstudio.summerschool.confirmation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import coil.transform.CircleCropTransformation
import ru.surfstudio.summerschool.confirmation.adapter.ContactsAdapter.ContactViewHolder
import ru.surfstudio.summerschool.data.SimilarContactsPair
import ru.surfstudio.summerschool.databinding.ItemContactBinding

class ContactsAdapter :
    ListAdapter<SimilarContactsPair, ContactViewHolder>(DiffUtilItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(
            ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class DiffUtilItemCallback : DiffUtil.ItemCallback<SimilarContactsPair>() {
        override fun areItemsTheSame(
            oldItem: SimilarContactsPair,
            newItem: SimilarContactsPair
        ): Boolean = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: SimilarContactsPair,
            newItem: SimilarContactsPair
        ): Boolean = oldItem == newItem
    }

    class ContactViewHolder(private val binding: ItemContactBinding) : ViewHolder(binding.root) {
        fun bind(contact: SimilarContactsPair) = with(binding) {
            ivAvatar.load(contact.contact.photoInfo?.photoUri) {
                transformations(CircleCropTransformation())
            }
            tvName.text = contact.contact.name
            rvPhones.adapter = PhonesAdapter().apply {
                submitList(contact.similarPhones.toList())
            }
        }
    }
}