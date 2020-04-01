package john.pazekha.krakow.transport.impl

import io.reactivex.Single
import john.pazekha.krakow.model.CvData
import retrofit2.Response
import retrofit2.http.GET

interface RestApi {
    @GET("neboskreb/0d22033b21e01fcf91562b8f5ea4244d/raw/krakow.data")
    fun rxFetchCvDataJson(): Single<Response<CvData>>
}