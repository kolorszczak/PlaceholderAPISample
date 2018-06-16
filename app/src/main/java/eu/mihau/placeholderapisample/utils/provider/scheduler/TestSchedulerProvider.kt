package eu.mihau.placeholderapisample.utils.provider.scheduler

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TestSchedulerProvider @Inject constructor() : SchedulerProvider {

    override fun io(): Scheduler = Schedulers.trampoline()
    override fun ui(): Scheduler = Schedulers.trampoline()
    override fun computation(): Scheduler = Schedulers.trampoline()
}
