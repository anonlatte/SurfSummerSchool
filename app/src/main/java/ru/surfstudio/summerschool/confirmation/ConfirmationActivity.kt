package ru.surfstudio.summerschool.confirmation

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import ru.surfstudio.summerschool.R
import ru.surfstudio.summerschool.SimilarContactsPair
import ru.surfstudio.summerschool.databinding.ActivityConfirmationBinding

class ConfirmationActivity : Activity() {

    private lateinit var binding: ActivityConfirmationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val contactsInfo: ArrayList<SimilarContactsPair> = intent.extras?.getParcelableArrayList(
            ARG_CONTACTS_LIST
        ) ?: arrayListOf()
        if (contactsInfo.isNotEmpty()) {
            showContacts(contactsInfo)
        } else {
            showEmptyScreen()
        }
    }

    private fun showContacts(contactsInfo: ArrayList<SimilarContactsPair>) {
        Toast.makeText(this, contactsInfo.toString(), Toast.LENGTH_LONG).show()
    }

    private fun showEmptyScreen() {
        Toast.makeText(this, R.string.empty_contacts, Toast.LENGTH_LONG).show()
    }

    companion object {
        const val ARG_CONTACTS_LIST = "argContactsList"
    }
}