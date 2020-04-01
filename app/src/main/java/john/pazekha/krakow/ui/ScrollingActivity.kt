package john.pazekha.krakow.ui

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import io.reactivex.disposables.Disposable
import john.pazekha.krakow.model.CvData
import john.pazekha.krakow.reactivex.DefaultSchedulerProvider
import john.pazekha.krakow.transport.impl.RetrofitTransport
import john.pazekha.krakow.R
import john.pazekha.krakow.databinding.ActivityScrollingBinding
import john.pazekha.krakow.model.*
import john.pazekha.krakow.ui.recycler.*


class ScrollingActivity : AppCompatActivity() {
    private val schedulers = DefaultSchedulerProvider()
    private val transport = RetrofitTransport(object : RetrofitTransport.Configuration {
                                override var baseUrl = "https://gist.githubusercontent.com"
                            })

    private lateinit var binding: ActivityScrollingBinding

    private var adapter: MultiTypeAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val contactInfo = getContactInfo(this)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_scrolling)

        setSupportActionBar(binding.toolbar)
        binding.lifecycleOwner = this
        binding.recycler.layoutManager = LinearLayoutManager(this)
        binding.model = contactInfo
        binding.controller = ClickHandler(contactInfo, this)

        fetchData()
    }

    private lateinit var disposable: Disposable

    private fun fetchData() {
        disposable = transport.rxFetchCvDetails()
            .subscribeOn(schedulers.io)
            .observeOn(schedulers.mainThread)
            .doFinally { disposable.dispose() }
            .subscribe(
                this::onFetchDataSuccess,
                this::onFetchDataFailure
            )
    }

    private fun onFetchDataSuccess(result: CvData) {
        val sections: List<Section> = ConversionHelper(this).toSections(result)
        adapter = MultiTypeAdapter(sections)
        binding.recycler.adapter = adapter

        // Expand the first section
        adapter!!.onGroupClick(0)
        sections[0].isExpanded.set(true)
    }


    private fun onFetchDataFailure(e: Throwable) {
        Snackbar.make(binding.root, "Error downloading data: $e", Snackbar.LENGTH_INDEFINITE).show()
    }

    private fun getContactInfo(context: Context): ContactInfo {
        return with(context) {
            ContactInfo(
                name = getString(R.string.JBM),
                role = getString(R.string.role),
                phone = getString(R.string.phone),
                email = getString(R.string.email),
                linkedin = getString(R.string.linkedin),
                showWhatsapp = true,
                showPhoneCall = true,
                showEmail = true,
                showWeb = true
            )
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        adapter?.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        adapter?.onRestoreInstanceState(savedInstanceState)
    }
}

