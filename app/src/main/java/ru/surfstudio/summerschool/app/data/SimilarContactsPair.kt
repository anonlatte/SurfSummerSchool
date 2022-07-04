package ru.surfstudio.summerschool.app.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Является парой контакт - контакты с одинаковым номером телефона.
 *
 * Наследуется от Parcelable, чтобы была возможность передавать объект между экранами.
 *
 * Аннотация [Parcelize] необходима для того, что автоматически для каждого параметра
 * была сгенерировано добавление в [Parcelable] объект.
 * См. [документацию](https://developer.android.com/kotlin/parcelize)
 *
 * @param contact Контакт
 * @param similarContacts список [контактов][ContactInfo]
 * @param isMarked статус выбранного, маркер для чекбокса
 */
@Parcelize
data class SimilarContactsPair(
    val contact: ContactInfo,
    val similarContacts: List<ContactInfo>,
    val isMarked: Boolean = false
) : Parcelable