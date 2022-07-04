package ru.surfstudio.summerschool.ui.main

import android.content.ContentResolver
import android.database.Cursor
import android.net.Uri
import android.provider.ContactsContract
import androidx.core.database.getStringOrNull
import androidx.lifecycle.ViewModel
import ru.surfstudio.summerschool.app.data.ContactInfo
import ru.surfstudio.summerschool.app.data.PhotoInfo
import ru.surfstudio.summerschool.app.data.SimilarContactsPair
import ru.surfstudio.summerschool.app.utils.PhoneUtils

class MainViewModel : ViewModel() {

    /** Возвращает список [SimilarContactsPair], взятый из ContentProvider'а. */
    fun getContactsInfo(contentResolver: ContentResolver): List<SimilarContactsPair> {
        val contactsQuery = contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            null,
            null,
            null,
            null
        ) ?: return emptyList()
        val parsedContacts: List<ContactInfo> = parseData(contentResolver, contactsQuery)
        contactsQuery.close()
        return groupBySimilar(parsedContacts)
    }

    /** Формирует из таблицы базы данных ContentProvider список [контактов][ContactInfo] */
    private fun parseData(
        contentResolver: ContentResolver,
        cursor: Cursor,
    ): List<ContactInfo> {
        val contacts = mutableListOf<ContactInfo>()
        while (cursor.moveToNext()) {
            val id = cursor.getStringByColumn(ContactInfo.FIELD_ID)
            val contactName = cursor.getStringByColumn(ContactInfo.FIELD_NAME)
            val contactPhoto = cursor.getStringByColumn(PhotoInfo.FIELD_PHOTO_URI)
            val contactPhotoThumb = cursor.getStringByColumn(PhotoInfo.FIELD_PHOTO_THUMB_URI)
            val photoInfo = if (contactPhoto != null && contactPhotoThumb != null) {
                PhotoInfo(Uri.parse(contactPhoto), Uri.parse(contactPhotoThumb))
            } else {
                null
            }
            val hasPhoneNumber = cursor.getStringByColumn(
                ContactsContract.Contacts.HAS_PHONE_NUMBER
            )
            val phones: List<String>? = if (hasPhoneNumber.equals("1")) {
                getPhonesForContact(contentResolver = contentResolver, contactId = id)
            } else {
                null
            }
            val contact = ContactInfo(
                id = id?.toLongOrNull() ?: -1L,
                name = contactName,
                photoInfo = photoInfo,
                phones = filterSimilarPhones(phones)
            )
            contacts.add(contact)
        }
        return contacts
    }

    /** По id пользователя вытаскивает номера телефонов из ContentProvider */
    private fun getPhonesForContact(
        contentResolver: ContentResolver,
        contactId: String?
    ): List<String> {
        val contactNumbers = mutableListOf<String>()
        val phonesCursor: Cursor? = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId,
            null,
            null
        )
        while (phonesCursor?.moveToNext() == true) {
            phonesCursor.getStringByColumn(ContactInfo.FIELD_PHONE)
                ?.let { contactNumbers.add(it) }
        }
        phonesCursor?.close()
        return contactNumbers
    }

    /** Возвращает список пар контакт и похожие контакты */
    private fun groupBySimilar(contactInfoList: List<ContactInfo>): List<SimilarContactsPair> {
        val groupedContacts = mutableListOf<SimilarContactsPair>()
        contactInfoList.forEach { comparedContact ->
            contactInfoList.filter { keyContactPhone ->
                val alreadyExistsInGroups = {
                    groupedContacts.any { contactsPair ->
                        contactsPair.contact.id == keyContactPhone.id
                    }
                }
                comparedContact.id != keyContactPhone.id
                    && !alreadyExistsInGroups()
                    && getSimilarPhones(comparedContact, keyContactPhone).isNotEmpty()
            }.let { contactsWithSamePhones ->
                if (contactsWithSamePhones.isNotEmpty()) {
                    groupedContacts.add(
                        SimilarContactsPair(comparedContact, contactsWithSamePhones)
                    )
                }
            }
        }
        return groupedContacts
    }

    /** Возвращает список пересекающихся контактов */
    private fun getSimilarPhones(source: ContactInfo, target: ContactInfo): Set<String> =
        source.phones?.intersect(target.phones?.toSet().orEmpty()).orEmpty()

    /** Фильтрует одинаковые номера после форматирования в формат 7xxxxxxxxxx */
    private fun filterSimilarPhones(phones: List<String>?): List<String> =
        phones?.map {
            val digitsOnly = it.replace("\\D+".toRegex(), "")
            if (digitsOnly.length == PhoneUtils.LENGTH_PHONE_WITH_CODE
                && digitsOnly.startsWith("8")
            ) {
                digitsOnly.replaceFirst("8", "7")
            } else {
                digitsOnly
            }
        }
            ?.distinct()
            .orEmpty()

    /** Заменяет вызов двух методов */
    private fun Cursor.getStringByColumn(column: String): String? {
        return getStringOrNull(getColumnIndex(column))
    }
}