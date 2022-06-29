package ru.surfstudio.summerschool.app.utils

import android.content.res.Resources

object UiUtils {
    val Int.px: Int
        get() = times(Resources.getSystem().displayMetrics.density).toInt()
}