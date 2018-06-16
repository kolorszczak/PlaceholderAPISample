package eu.mihau.placeholderapisample.utils.provider.scheduler

import io.reactivex.Scheduler

interface SchedulerProvider {

    fun io(): Scheduler
    fun ui(): Scheduler
    fun computation(): Scheduler
}
