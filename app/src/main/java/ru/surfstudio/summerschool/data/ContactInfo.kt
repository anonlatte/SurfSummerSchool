package ru.surfstudio.summerschool.data

import android.os.Parcelable
import android.provider.ContactsContract
import kotlinx.parcelize.Parcelize

@Parcelize
data class ContactInfo(
    val id: Long,
    val name: String?,
    val photoInfo: PhotoInfo?,
    val phones: List<String>?
) : Parcelable {

    companion object {
        const val FIELD_ID = ContactsContract.Contacts._ID
        const val FIELD_NAME = ContactsContract.Contacts.DISPLAY_NAME
        const val FIELD_PHONE = ContactsContract.CommonDataKinds.Phone.NUMBER
    }
}