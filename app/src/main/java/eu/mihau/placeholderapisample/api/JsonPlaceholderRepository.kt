package eu.mihau.placeholderapisample.api

import eu.mihau.placeholderapisample.utils.provider.scheduler.SchedulerProvider
import javax.inject.Inject

class JsonPlaceholderRepository @Inject constructor(private val apiService: APIService, private val schedulerProvider: SchedulerProvider) {

}