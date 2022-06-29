package ru.surfstudio.summerschool.data

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
data class SimilarContactsPair(
    val contact: ContactInfo,
    val similarContacts: List<ContactInfo>
) : Parcelable {
    @IgnoredOnParcel
    val similarPhones: Set<String> = contact.phones?.intersect(
        similarContacts.flatMap { it.phones.orEmpty() }.toSet()
    ).orEmpty()
}