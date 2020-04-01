package john.pazekha.krakow.transport.impl

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.reactivex.Single
import john.pazekha.krakow.model.CvData
import john.pazekha.krakow.transport.ITransport
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitTransport(private val configuration: Configuration) : ITransport
{
    interface Configuration {
        var baseUrl: String
    }

    private val gson: Gson = GsonBuilder()
        .setLenient()
        .setPrettyPrinting()
        .create()


    override fun rxFetchCvDetails(): Single<CvData> {
        val retrofit = Retrofit.Builder()
            .baseUrl(configuration.baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        val service = retrofit.create(RestApi::class.java)

        return service.rxFetchCvDataJson().map{ response -> response.body()!! }
    }
}