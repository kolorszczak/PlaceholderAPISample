package eu.mihau.placeholderapisample.utils.provider.resource

import dagger.Module
import eu.mihau.placeholderapisample.R

@Module
class TestResourceProvider : ResourceProvider {
    override fun getColor(resId: Int): Int = R.color.md_amber_100

    override fun getString(resId: Int): String = "mock"

    override fun getErrorMessage(throwable: Throwable): String = "Mock Error"

    override fun getErrorCode(throwable: Throwable): Int = 500
}
