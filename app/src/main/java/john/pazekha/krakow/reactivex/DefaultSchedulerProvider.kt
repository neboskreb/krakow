package john.pazekha.krakow.reactivex

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DefaultSchedulerProvider : ISchedulerProvider {
    override var io          = Schedulers.io()
    override var computation = Schedulers.computation()
    override var mainThread  = AndroidSchedulers.mainThread()
}