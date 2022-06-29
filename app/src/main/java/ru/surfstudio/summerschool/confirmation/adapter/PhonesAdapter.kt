package ru.surfstudio.summerschool.confirmation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.surfstudio.summerschool.confirmation.adapter.PhonesAdapter.PhoneViewHolder
import ru.surfstudio.summerschool.databinding.ItemPhoneBinding

class PhonesAdapter : ListAdapter<String, PhoneViewHolder>(DiffUtilItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneViewHolder {
        return PhoneViewHolder(
            ItemPhoneBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: PhoneViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class DiffUtilItemCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
            oldItem == newItem
    }

    class PhoneViewHolder(private val binding: ItemPhoneBinding) : ViewHolder(binding.root) {

        fun bind(phone: String) {
            binding.root.text = phone
        }
    }
}