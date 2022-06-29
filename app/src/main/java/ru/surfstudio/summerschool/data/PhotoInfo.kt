package ru.surfstudio.summerschool.data

import android.net.Uri
import android.os.Parcelable
import android.provider.ContactsContract
import kotlinx.parcelize.Parcelize

@Parcelize
data class PhotoInfo(
    val photoUri: Uri,
    val photoThumbUri: Uri
) : Parcelable {
    companion object {
        const val FIELD_PHOTO_URI = ContactsContract.Contacts.PHOTO_URI
        const val FIELD_PHOTO_THUMB_URI = ContactsContract.Contacts.PHOTO_THUMBNAIL_URI
    }
}