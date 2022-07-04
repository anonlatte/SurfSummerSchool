package ru.surfstudio.summerschool.app.data

import android.net.Uri
import android.os.Parcelable
import android.provider.ContactsContract
import kotlinx.parcelize.Parcelize
import ru.surfstudio.summerschool.app.data.PhotoInfo.Companion.FIELD_PHOTO_THUMB_URI
import ru.surfstudio.summerschool.app.data.PhotoInfo.Companion.FIELD_PHOTO_URI

/**
 * Информация о фото.
 *
 * Наследуется от Parcelable, чтобы была возможность передавать объект между экранами.
 *
 * Аннотация [Parcelize] необходима для того, что автоматически для каждого параметра
 * была сгенерировано добавление в [Parcelable] объект.
 * См. [документацию](https://developer.android.com/kotlin/parcelize)
 *
 * @param photoUri uri основного фото
 * @param photoThumbUri uri сжатого превью
 * @property FIELD_PHOTO_URI необходим для получения столбца [photoUri] из [android.database.Cursor]
 * @property FIELD_PHOTO_THUMB_URI необходим для получения столбца [photoThumbUri] из [android.database.Cursor]
 */
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