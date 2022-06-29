package ru.surfstudio.summerschool

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ru.surfstudio.summerschool.data.ContactInfo

@Parcelize
data class SimilarContactsPair(
    val contact: ContactInfo,
    val similarContact: List<ContactInfo>
) : Parcelable {
    val similarPhones: Set<String> = contact.phones?.intersect(
        similarContact.flatMap { it.phones.orEmpty() }.toSet()
    ).orEmpty()
}