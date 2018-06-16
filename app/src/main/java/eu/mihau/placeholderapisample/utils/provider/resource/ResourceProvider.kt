package eu.mihau.placeholderapisample.utils.provider.resource

import android.support.annotation.ColorRes
import android.support.annotation.StringRes

interface ResourceProvider {

    fun getColor(@ColorRes resId: Int): Int
    fun getString(@StringRes resId: Int): String
    fun getErrorMessage(throwable: Throwable): String
    fun getErrorCode(throwable: Throwable): Int
}
