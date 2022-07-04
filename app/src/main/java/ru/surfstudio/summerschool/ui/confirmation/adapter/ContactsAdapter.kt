package ru.surfstudio.summerschool.ui.confirmation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import coil.transform.CircleCropTransformation
import ru.surfstudio.summerschool.R
import ru.surfstudio.summerschool.app.data.ContactInfo
import ru.surfstudio.summerschool.databinding.ItemContactBinding
import ru.surfstudio.summerschool.ui.confirmation.adapter.ContactsAdapter.ContactViewHolder

/** Для отображения контактов */
class ContactsAdapter : ListAdapter<ContactInfo, ContactViewHolder>(
    DiffUtilItemCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(
            ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class DiffUtilItemCallback : DiffUtil.ItemCallback<ContactInfo>() {
        override fun areItemsTheSame(
            oldItem: ContactInfo,
            newItem: ContactInfo
        ): Boolean = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: ContactInfo,
            newItem: ContactInfo
        ): Boolean = oldItem == newItem
    }

    class ContactViewHolder(
        private val binding: ItemContactBinding
    ) : ViewHolder(binding.root) {
        fun bind(contact: ContactInfo) = with(binding) {
            // Автоматически загружает картинку по URI
            ivAvatar.load(contact.photoInfo?.photoUri ?: R.drawable.placeholder) {
                transformations(CircleCropTransformation())
            }
            tvName.text = contact.name
            // Создаем список телефонных номеров
            rvPhones.adapter = PhonesAdapter().apply {
                submitList(contact.phones?.toList())
            }
        }
    }
}