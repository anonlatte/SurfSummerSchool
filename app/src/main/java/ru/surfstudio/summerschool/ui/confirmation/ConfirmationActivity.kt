package ru.surfstudio.summerschool.ui.confirmation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ru.surfstudio.summerschool.R
import ru.surfstudio.summerschool.app.ui.SpacesItemDecoration
import ru.surfstudio.summerschool.app.utils.UiUtils.px
import ru.surfstudio.summerschool.data.SimilarContactsPair
import ru.surfstudio.summerschool.databinding.ActivityConfirmationBinding
import ru.surfstudio.summerschool.ui.confirmation.adapter.SimilarContactsAdapter

class ConfirmationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConfirmationBinding
    private lateinit var contactsAdapter: SimilarContactsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvContacts.apply {
            contactsAdapter = SimilarContactsAdapter { clickedCard ->
                contactsAdapter.submitList(contactsAdapter.currentList.map {
                    if (clickedCard.contact.id == it.contact.id) {
                        it.copy(isMarked = clickedCard.isMarked)
                    } else {
                        it
                    }
                })
            }
            adapter = contactsAdapter
            addItemDecoration(SpacesItemDecoration(16.px))
        }

        val contactsInfo: ArrayList<SimilarContactsPair> = intent.extras?.getParcelableArrayList(
            ARG_CONTACTS_LIST
        ) ?: arrayListOf()
        if (contactsInfo.isNotEmpty()) {
            showContacts(contactsInfo)
        } else {
            showEmptyScreen()
        }
    }

    override fun onDestroy() {
        binding.rvContacts.adapter = null
        super.onDestroy()
    }

    private fun showContacts(contactsInfo: ArrayList<SimilarContactsPair>) {
        title = getString(R.string.title_confirmation, contactsInfo.size)
        contactsAdapter.submitList(contactsInfo)
    }

    private fun showEmptyScreen() {
        title = getString(R.string.title_confirmation_not_found)
        Toast.makeText(this, R.string.empty_contacts, Toast.LENGTH_LONG).show()
    }

    companion object {
        const val ARG_CONTACTS_LIST = "argContactsList"
    }
}