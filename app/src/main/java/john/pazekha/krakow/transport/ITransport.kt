package john.pazekha.krakow.transport

import io.reactivex.Single
import john.pazekha.krakow.model.CvData

interface ITransport {
    fun rxFetchCvDetails() : Single<CvData>
}