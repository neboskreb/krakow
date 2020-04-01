package john.pazekha.ebay.reactivex

import io.reactivex.Scheduler
import john.pazekha.krakow.reactivex.ISchedulerProvider

class TestSchedulerProvider(scheduler: Scheduler) :
    ISchedulerProvider {
    override var io = scheduler
    override var computation = scheduler
    override var mainThread = scheduler
}