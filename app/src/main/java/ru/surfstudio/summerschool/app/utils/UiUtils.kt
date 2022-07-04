package ru.surfstudio.summerschool.app.utils

import android.content.res.Resources
import ru.surfstudio.summerschool.app.utils.UiUtils.px

/** @property px переводит dp в пискели */
object UiUtils {
    val Int.px: Int
        get() = times(Resources.getSystem().displayMetrics.density).toInt()
}