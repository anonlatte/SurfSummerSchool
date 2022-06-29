package ru.surfstudio.summerschool

import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.database.getStringOrNull
import ru.surfstudio.summerschool.PhoneUtils.LENGTH_CODE_RU
import ru.surfstudio.summerschool.PhoneUtils.LENGTH_PHONE_WITH_CODE
import ru.surfstudio.summerschool.confirmation.ConfirmationActivity
import ru.surfstudio.summerschool.data.ContactInfo
import ru.surfstudio.summerschool.data.PhotoInfo
import ru.surfstudio.summerschool.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val contactsPermissionsLauncher = registerForActivityResult(
        RequestPermission(), ::handlePermissions
    )

    private fun handlePermissions(isGranted: Boolean?) {
        if (isGranted == true) {
            goToConfirmation()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnAction.setOnClickListener {
            if (arePermissionsGranted()) {
                goToConfirmation()
            } else {
                contactsPermissionsLauncher.launch(android.Manifest.permission.READ_CONTACTS)
            }
        }
    }

    private fun goToConfirmation() {
        val contactsInfo: ArrayList<SimilarContactsPair> =
            ArrayList(groupBySimilar(getContactsInfo()))
        val intent = Intent(this, ConfirmationActivity::class.java)
            .putParcelableArrayListExtra(ConfirmationActivity.ARG_CONTACTS_LIST, contactsInfo)
        startActivity(intent)
    }

    private fun arePermissionsGranted(): Boolean = ContextCompat.checkSelfPermission(
        this, android.Manifest.permission.READ_CONTACTS
    ) == PackageManager.PERMISSION_GRANTED

    private fun getContactsInfo(): List<ContactInfo> {
        val contacts = mutableListOf<ContactInfo>()
        val cursor = contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            null,
            null,
            null,
            null
        ) ?: return contacts
        while (cursor.moveToNext()) {
            val id = cursor.getStringOrNull(
                cursor.getColumnIndexOrThrow(ContactInfo.FIELD_ID)
            )
            val contactName = cursor.getStringOrNull(
                cursor.getColumnIndex(ContactInfo.FIELD_NAME)
            )
            val contactPhoto = cursor.getStringOrNull(
                cursor.getColumnIndex(PhotoInfo.FIELD_PHOTO_URI)
            )
            val contactPhotoThumb = cursor.getStringOrNull(
                cursor.getColumnIndex(PhotoInfo.FIELD_PHOTO_THUMB_URI)
            )
            val photoInfo = if (contactPhoto != null && contactPhotoThumb != null) {
                PhotoInfo(contactPhoto, contactPhotoThumb)
            } else {
                null
            }
            val phones = getPhonesForContact(contactId = id).takeIf {
                val columnIndex = cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)
                cursor.getStringOrNull(columnIndex).equals("1")
            }
            val contact = ContactInfo(
                id = id?.toLongOrNull() ?: -1L,
                name = contactName,
                photoInfo = photoInfo,
                phones = filterSimilarPhones(phones)
            )
            contacts.add(contact)
        }
        cursor.close()
        return contacts
    }

    private fun getPhonesForContact(contactId: String?): List<String> {
        val contactNumbers = mutableListOf<String>()
        val phonesCursor: Cursor? = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId,
            null,
            null
        )
        while (phonesCursor?.moveToNext() == true) {
            val columnIndex = phonesCursor.getColumnIndex(ContactInfo.FIELD_PHONE)
            phonesCursor.getStringOrNull(columnIndex)
                ?.let { contactNumbers.add(it) }
        }
        phonesCursor?.close()
        return contactNumbers
    }

    companion object {
        fun groupBySimilar(contactInfoList: List<ContactInfo>): List<SimilarContactsPair> {
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

        fun getSimilarPhones(source: ContactInfo, target: ContactInfo): Set<String> =
            source.phones?.intersect(target.phones?.toSet().orEmpty()).orEmpty()

        fun containsPhone(phones: List<String>?, phone: String): Boolean {
            return phones?.any { comparedContactPhone ->
                val sameLength = phone.length == comparedContactPhone.length
                val equalsWithoutRuCode = sameLength
                    && phone.substring(LENGTH_CODE_RU) == comparedContactPhone.substring(
                    LENGTH_CODE_RU
                )
                phone == comparedContactPhone || equalsWithoutRuCode
            } == true
        }

        fun filterSimilarPhones(phones: List<String>?): List<String> =
            phones?.map {
                val digitsOnly = it.replace("\\D+".toRegex(), "")
                if (digitsOnly.length == LENGTH_PHONE_WITH_CODE && digitsOnly.startsWith("8")) {
                    digitsOnly.replaceFirst("8", "7")
                } else {
                    digitsOnly
                }
            }
                ?.distinct()
                .orEmpty()
    }
}