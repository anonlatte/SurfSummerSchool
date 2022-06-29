package ru.surfstudio.summerschool.ui.confirmation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.surfstudio.summerschool.data.SimilarContactsPair
import ru.surfstudio.summerschool.databinding.ItemSimilarContactsBinding
import ru.surfstudio.summerschool.ui.confirmation.adapter.SimilarContactsAdapter.SimilarContactsAdapter

class SimilarContactsAdapter(val onCheckedChangeListener: (SimilarContactsPair) -> Unit) :
    ListAdapter<SimilarContactsPair, SimilarContactsAdapter>(DiffUtilItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarContactsAdapter {
        return SimilarContactsAdapter(
            ItemSimilarContactsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: SimilarContactsAdapter, position: Int) {
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

    inner class SimilarContactsAdapter(private val binding: ItemSimilarContactsBinding) :
        ViewHolder(binding.root) {
        fun bind(contactsPair: SimilarContactsPair) = with(binding) {
            rvSimilarContacts.adapter = ContactsAdapter().apply {
                submitList(listOf(contactsPair.contact) + contactsPair.similarContacts)
            }
            checkbox.isChecked = contactsPair.isMarked
            checkbox.setOnClickListener {
                onCheckedChangeListener(contactsPair.copy(isMarked = checkbox.isChecked))
                flowButtons.isVisible = !checkbox.isChecked
            }
        }
    }
}