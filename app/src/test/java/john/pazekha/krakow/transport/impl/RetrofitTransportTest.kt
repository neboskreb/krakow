package john.pazekha.krakow.transport.impl

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

private const val RESPONSE_BODY = "{\"summary\":{\"formattedSummary\":\"20+ years of experience aiming at long-term results, customer satisfaction \"},\"jobs\":[{\"logoUrl\":\"https://gist.githubusercontent.com/neboskreb/0d22033b21e01fcf91562b8f5ea4244d/raw/krakow.logo_airtop.png\",\"dates\":\"2019-2020 (4 months)\",\"title\":\"Senior Java Developer\",\"company\":\"AirTOp Soft\",\"description\":\"Fast-time simulation software for Air Traffic Control (ATC).\"},{\"logoUrl\":\"https://gist.githubusercontent.com/neboskreb/0d22033b21e01fcf91562b8f5ea4244d/raw/krakow.logo_getjet.png\",\"dates\":\"2019 (6 months)\",\"title\":\"Senior Java Developer\",\"company\":\"GetJet Airlines\",\"description\":\"Middle layer for connecting the frontend facade to back office information services, to allow the airline a smooth transition to paperless operation (“electronic flight bag”).\"},{\"logoUrl\":\"https://gist.githubusercontent.com/neboskreb/0d22033b21e01fcf91562b8f5ea4244d/raw/krakow.logo_db.png\",\"dates\":\"2017-2018 (1,5 years)\",\"title\":\"Senior Android Developer\",\"company\":\"Deutsche Bahn\",\"description\":\"Application for train drivers’ daily tasks.\"},{\"logoUrl\":\"https://gist.githubusercontent.com/neboskreb/0d22033b21e01fcf91562b8f5ea4244d/raw/krakow.logo_tomtom.png\",\"dates\":\"2017 (4 months)\",\"title\":\"Senior Java Developer\",\"company\":\"TomTom\",\"description\":\"Service for producing the typing suggestions in a navigation application. Powered by a massive\\nLucene database. The search is typo-tolerant and phonetic typing capable.\"},{\"logoUrl\":\"https://gist.githubusercontent.com/neboskreb/0d22033b21e01fcf91562b8f5ea4244d/raw/krakow.logo_qelp.png\",\"dates\":\"2015-2016 (1 year)\",\"title\":\"Lead Mobile Developer\",\"company\":\"Qelp\",\"description\":\"Laid down the foundation for Manage-yourown-device app which is a part of Qelp’s 360-degree solution for the consumer support. The app was included in few pilots with leading Dutch and Brazilian brands and received favorable feedback.\"},{\"logoUrl\":\"https://gist.githubusercontent.com/neboskreb/0d22033b21e01fcf91562b8f5ea4244d/raw/krakow.logo_icemobile.png\",\"dates\":\"2013-2015 (2 years)\",\"title\":\"Android Technical Lead\",\"company\":\"IceMobile\",\"description\":\"4 projects released: ABN AMRO 6.1, 6.2, 6.3, and the fully redesigned ABN AMRO 7.0!\"},{\"logoUrl\":\"https://gist.githubusercontent.com/neboskreb/0d22033b21e01fcf91562b8f5ea4244d/raw/krakow.logo_icemobile.png\",\"dates\":\"2013\",\"title\":\"Junior Java EE developer\",\"company\":\"IceMobile\",\"description\":\"As a side-assignment, working in a team of three very experienced Java EE developers. Learned really a lot from them.\"},{\"logoUrl\":\"https://gist.githubusercontent.com/neboskreb/0d22033b21e01fcf91562b8f5ea4244d/raw/krakow.logo_icemobile.png\",\"dates\":\"2013\",\"title\":\"Senior Android developer\",\"company\":\"IceMobile\",\"description\":\"2 projects released: ABN AMRO 4.0, 6.0. Promoted to Android Technical Lead\"},{\"logoUrl\":\"https://gist.githubusercontent.com/neboskreb/0d22033b21e01fcf91562b8f5ea4244d/raw/krakow.logo_icemobile.png\",\"dates\":\"2010-2013 (2 years)\",\"title\":\"Android developer\",\"company\":\"IceMobile\",\"description\":\"4 projects released: ABN AMRO 1.0, TV-Gids, Stats Loterij, ABN AMRO 3.0. Promoted to Senior Android developer\"},{\"logoUrl\":\"https://gist.githubusercontent.com/neboskreb/0d22033b21e01fcf91562b8f5ea4244d/raw/krakow.logo_stanfy.png\",\"dates\":\"2010\",\"title\":\"Android developer\",\"company\":\"Stanfy\",\"description\":\"1 project released: AlterGeo for Android version 1.0 (see www.altergeo.com )\"}],\"education\":[{\"period\":\"1995–2001\",\"school\":\"National Aerospace University “KhAI”, Kharkov, Ukraine\\nMaster in Computer science\"},{\"period\":\"1985–1995\",\"school\":\"School №17, Feodosia, Soviet Union/Ukraine\"}],\"skills\":[{\"field\":\"Development process:\",\"skills\":[\"Agile/Scrum\",\"Agile with distributed teams\"]},{\"field\":\"Continuous Integration/Delivery:\",\"skills\":[\"GIT, SVN, Bitbucket\",\"Jira + Greenhopper + Confluence\",\"Gradle, Maven, Jenkins\"]},{\"field\":\"Test-driven development:\",\"skills\":[\"JUnit\",\"Mockito, JMockit\"]},{\"field\":\"Architecture:\",\"skills\":[\"SOLID principle\",\"DRY principle\",\"Design patterns\",\"Abstraction\",\"System analysis, Use-cases\",\"UML, SRS, OOD/OOP, CRC\"]}]}"

class RetrofitTransportTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var configuration: RetrofitTransport.Configuration

    @Before
    fun before() {
        mockWebServer = MockWebServer()
        configuration = object : RetrofitTransport.Configuration {
            override var baseUrl: String =  mockWebServer.url("").toString()
        }
    }

    @After
    fun after() {
        mockWebServer.shutdown()
    }

    @Test
    fun getItemDetailsSuccess() {
        /** ***** GIVEN ***** */
        mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(RESPONSE_BODY))

        val transport = RetrofitTransport(configuration)

        /** ***** WHEN  ***** */
        val single = transport.rxFetchCvDetails()

        /** ***** THEN  ***** */
        with(single.test()) {
            assertComplete()
            assertNoErrors()

            val result = values()[0]
            assertNotNull(result.summary)
            assertNotNull(result.jobs)
            assertNotNull(result.education)
            assertNotNull(result.skills)
        }
    }
}