package eu.mihau.placeholderapisample.utils.provider.resource

import android.content.Context
import android.support.annotation.ColorRes
import android.support.annotation.StringRes
import android.support.v4.content.ContextCompat
import com.google.gson.Gson
import eu.mihau.placeholderapisample.R
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class AppResourcesProvider @Inject constructor(private val context: Context) : ResourceProvider {

    override fun getColor(@ColorRes resId: Int): Int = ContextCompat.getColor(context, resId)

    override fun getString(@StringRes resId: Int): String = context.getString(resId)

    override fun getErrorMessage(throwable: Throwable): String {
        if (throwable is HttpException && throwable.response().code() < 500) {
            val responseBody = throwable.response().errorBody()
            if (responseBody != null) {
                val json = responseBody.string().trim { it <= ' ' }.replace("\n".toRegex(), "").replace("\r".toRegex(), "")
                return Gson().fromJson(json, Error::class.java).message
                        ?: getString(R.string.serverError)
            }
        } else if (throwable is UnknownHostException || throwable is SocketTimeoutException) {
            return getString(R.string.noInternetConnection)
        }
        return getString(R.string.serverError)
    }

    override fun getErrorCode(throwable: Throwable): Int {
        if (throwable is HttpException) {
            return throwable.response().code()
        } else if (throwable is UnknownHostException || throwable is SocketTimeoutException) {
            return 503
        }
        return 500
    }
}
