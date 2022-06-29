package ru.surfstudio.summerschool.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SimilarContactsPair(
    val contact: ContactInfo,
    val similarContacts: List<ContactInfo>,
    val isMarked: Boolean = false
) : Parcelable