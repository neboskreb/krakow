package john.pazekha.krakow.reactivex

import io.reactivex.Scheduler

interface ISchedulerProvider {
    var io: Scheduler
    var computation: Scheduler
    var mainThread: Scheduler
}

