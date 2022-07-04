package ru.surfstudio.summerschool.app.data

import android.os.Parcelable
import android.provider.ContactsContract
import kotlinx.parcelize.Parcelize
import ru.surfstudio.summerschool.app.data.ContactInfo.Companion.FIELD_ID
import ru.surfstudio.summerschool.app.data.ContactInfo.Companion.FIELD_NAME
import ru.surfstudio.summerschool.app.data.ContactInfo.Companion.FIELD_PHONE

/**
 * Информация контакта.
 *
 * Наследуется от Parcelable, чтобы была возможность передавать объект между экранами.
 *
 * Аннотация [Parcelize] необходима для того, что автоматически для каждого параметра
 * была сгенерировано добавление в [Parcelable] объект.
 * См. [документацию](https://developer.android.com/kotlin/parcelize)
 *
 * @param id id в таблице базы данных
 * @param name имя контакта
 * @param photoInfo фотография контакта
 * @param phones список телефонных номеров
 * @property FIELD_ID необходим для получения столбца [id] из [android.database.Cursor]
 * @property FIELD_NAME необходим для получения столбца [name] из [android.database.Cursor]
 * @property FIELD_PHONE необходим для получения столбца [phones] из [android.database.Cursor]
 */
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