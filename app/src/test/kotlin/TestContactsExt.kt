import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import ru.surfstudio.summerschool.MainActivity
import ru.surfstudio.summerschool.data.ContactInfo
import ru.surfstudio.summerschool.data.SimilarContactsPair
import kotlin.random.Random
import kotlin.random.nextInt

class TestContactsExt {

    @Test
    fun testFilterSimilarPhones() {
        val source = listOf(
            "+7 (994) 890-99-90",
            "8(994)890-99-90",
            "89948909990",
            "+7 (902) 771-25-31",
            "+7(902)7712531",
            "79027712531",
            "+7 (981) 317-50-89",
            "+79813175089",
        )
        val result = listOf(
            "79948909990",
            "89948909990",
            "79027712531",
            "79813175089",
        )
        assertThat(result, equalTo(MainActivity.filterSimilarPhones(source)))
    }

    @Test
    fun testContainsPhones() {
        val source = listOf(
            "79948909990",
            "89948909990",
            "79027712531",
            "79813175089",
        )
        val containedPhone = "79813175089"
        val randomPhone = "79517854732"

        assertTrue(MainActivity.containsPhone(source, containedPhone))
        assertFalse(MainActivity.containsPhone(emptyList(), containedPhone))
        assertFalse(MainActivity.containsPhone(source, randomPhone))
    }

    @Test
    fun testGroupBySimilar() {
        val source = listOf(
            ContactInfo(
                id = 0,
                name = null,
                photoInfo = null,
                phones = listOf("79943131182", "79312832554", "79586592924", "79768337350")
            ),
            ContactInfo(id = 1, name = null, photoInfo = null, phones = listOf("79672853700")),
            ContactInfo(
                id = 2,
                name = null,
                photoInfo = null,
                phones = listOf("79855583032", "79835351000", "79860186529", "79641922209")
            ),
            ContactInfo(
                id = 3,
                name = null,
                photoInfo = null,
                phones = listOf("79421793574", "79673329368", "79961727250")
            ),
            ContactInfo(
                id = 4,
                name = null,
                photoInfo = null,
                phones = listOf("79352637816", "79479168775")
            ),
            ContactInfo(id = 5, name = null, photoInfo = null, phones = listOf("79217073545")),
            ContactInfo(
                id = 6,
                name = null,
                photoInfo = null,
                phones = listOf("79024276553", "79790715752", "79452737482")
            ),
            ContactInfo(
                id = 7,
                name = null,
                photoInfo = null,
                phones = listOf("79374932934", "79224760349", "79151414125", "79883485747")
            ),
            ContactInfo(
                id = 8,
                name = null,
                photoInfo = null,
                phones = listOf("79246979409", "79487548078")
            ),
            ContactInfo(
                id = 9,
                name = null,
                photoInfo = null,
                phones = listOf("79052595578", "79584956378", "79005596538", "79961727250")
            ),
            ContactInfo(id = 10, name = null, photoInfo = null, phones = listOf("79606558704")),
            ContactInfo(
                id = 11,
                name = null,
                photoInfo = null,
                phones = listOf("79524809145", "79610674597")
            ),
            ContactInfo(
                id = 12,
                name = null,
                photoInfo = null,
                phones = listOf("79613566857", "79690952675", "79212465859", "79815863301")
            ),
            ContactInfo(
                id = 13,
                name = null,
                photoInfo = null,
                phones = listOf("79882230551", "79028106809", "79524125543")
            ),
            ContactInfo(id = 14, name = null, photoInfo = null, phones = listOf("79954206504")),
            ContactInfo(
                id = 15,
                name = null,
                photoInfo = null,
                phones = listOf("79715864627", "79673329368", "79098379361")
            ),
            ContactInfo(id = 16, name = null, photoInfo = null, phones = listOf("79788503817")),
            ContactInfo(
                id = 17,
                name = null,
                photoInfo = null,
                phones = listOf("79026136334", "79522111157", "79169781511", "79052434670")
            ),
            ContactInfo(
                id = 18,
                name = null,
                photoInfo = null,
                phones = listOf("79254820868", "79005596538", "79196252005", "79673329368")
            ),
            ContactInfo(
                id = 19,
                name = null,
                photoInfo = null,
                phones = listOf("79395974351", "79036265459", "79974777314", "79605337137")
            ),
            ContactInfo(
                id = 20,
                name = null,
                photoInfo = null,
                phones = listOf("79953275445", "79630892094")
            ),
            ContactInfo(
                id = 21,
                name = null,
                photoInfo = null,
                phones = listOf("79783546060", "79952385672")
            ),
            ContactInfo(id = 22, name = null, photoInfo = null, phones = listOf("79013100153")),
            ContactInfo(id = 23, name = null, photoInfo = null, phones = listOf("79384633861")),
            ContactInfo(
                id = 24,
                name = null,
                photoInfo = null,
                phones = listOf("79970773976", "79211298931", "79766177986")
            ),
            ContactInfo(id = 25, name = null, photoInfo = null, phones = listOf("79196252005"))
        )
        val result = listOf(
            SimilarContactsPair(
                contact = ContactInfo(
                    id = 3,
                    name = null,
                    photoInfo = null,
                    phones = listOf("79421793574", "79673329368", "79961727250")
                ),
                similarContacts = listOf(
                    ContactInfo(
                        id = 9,
                        name = null,
                        photoInfo = null,
                        phones = listOf("79052595578", "79584956378", "79005596538", "79961727250")
                    ),
                    ContactInfo(
                        id = 15,
                        name = null,
                        photoInfo = null,
                        phones = listOf("79715864627", "79673329368", "79098379361")
                    ),
                    ContactInfo(
                        id = 18,
                        name = null,
                        photoInfo = null,
                        phones = listOf("79254820868", "79005596538", "79196252005", "79673329368")
                    )
                )
            ),
            SimilarContactsPair(
                contact = ContactInfo(
                    id = 9,
                    name = null,
                    photoInfo = null,
                    phones = listOf("79052595578", "79584956378", "79005596538", "79961727250")
                ),
                similarContacts = listOf(
                    ContactInfo(
                        id = 18,
                        name = null,
                        photoInfo = null,
                        phones = listOf("79254820868", "79005596538", "79196252005", "79673329368")
                    )
                )
            ),
            SimilarContactsPair(
                contact = ContactInfo(
                    id = 15,
                    name = null,
                    photoInfo = null,
                    phones = listOf("79715864627", "79673329368", "79098379361")
                ),
                similarContacts = listOf(
                    ContactInfo(
                        id = 18,
                        name = null,
                        photoInfo = null,
                        phones = listOf("79254820868", "79005596538", "79196252005", "79673329368")
                    )
                )
            ),
            SimilarContactsPair(
                contact = ContactInfo(
                    id = 18,
                    name = null,
                    photoInfo = null,
                    phones = listOf("79254820868", "79005596538", "79196252005", "79673329368")
                ),
                similarContacts = listOf(
                    ContactInfo(
                        id = 25,
                        name = null,
                        photoInfo = null,
                        phones = listOf("79196252005")
                    )
                )
            )
        )
        assertThat(MainActivity.groupBySimilar(source), equalTo(result))
    }

    @Suppress("unused")
    private fun getRandomContactsData(): List<ContactInfo> {
        val phoneSource: List<String> = listOf(
            "79954206504", "79953275445", "79111236854", "79545902502", "79598875349",
            "79098379361", "79715864627", "79045977704", "79524809145", "79790715752",
            "79613566857", "79855583032", "79080671328", "79045040956", "79835351000",
            "79685465291", "79315934585", "79769986598", "79679358882", "79052740758",
            "79678468246", "79961446714", "79135733587", "79149048078", "79867167340",
            "79013100153", "79175678911", "79396415540", "79247823872", "79865264496",
            "79036265459", "79224225121", "79211298931", "79779020793", "79606223417",
            "79281857256", "79421793574", "79254820868", "79598950711", "79092028724",
            "79203622090", "79605337137", "79815863301", "79943131182", "79354859924",
            "79026136334", "79646634867", "79257477937", "79918336173", "79014146725",
            "79352637816", "79902901816", "79005596538", "79263138826", "79437213496",
            "79783546060", "79407741575", "79262103861", "79127798979", "79672853700",
            "79077179333", "79699069906", "79354040960", "79620899727", "79794569831",
            "79768337350", "79246979409", "79733920449", "79293978089", "79524125543",
            "79452737482", "79787150669", "79098313153", "79202973144", "79032732216",
            "79217073545", "79952385672", "79479168775", "79980832272", "79194999662",
            "79359253157", "79436782357", "79708093059", "79169781511", "79147007765",
            "79477047008", "79499796385", "79028106809", "79495071830", "79232034481",
            "79560427525", "79958497597", "79970773976", "79151414125", "79312832554",
            "79932840897", "79434499319", "79601644866", "79450084511", "79116646598",
            "79076588025", "79497895553", "79951907951", "79395974351", "79974777314",
            "79484919026", "79228622916", "79522111157", "79770603469", "79583630475",
            "79915815168", "79889217646", "79224760349", "79773396615", "79565536230",
            "79730910274", "79707370863", "79487548078", "79384633861", "79064977694",
            "79628748258", "79705986643", "79641922209", "79224259910", "79562920418",
            "79346289864", "79577158923", "79075912918", "79223087271", "79882230551",
            "79336804417", "79788503817", "79024276553", "79823462520", "79584956378",
            "79114352423", "79471370991", "79173794785", "79610674597", "79271262688",
            "79072279234", "79673329368", "79658067991", "79911112784", "79880047231",
            "79213359170", "79447171154", "79212465859", "79630892094", "79114353679",
            "79199097591", "79883485747", "79272972427", "79207073232", "79922793507",
            "79870813485", "79690952675", "79719463187", "79976316693", "79606558704",
            "79931321702", "79559779306", "79774756323", "79179325172", "79766177986",
            "79312156680", "79994846722", "79860186529", "79052434670", "79374932934",
            "79393100842", "79586592924", "79181818512", "79873328215", "79378498008",
            "79723983439", "79196252005", "79052595578", "79961727250", "79608549237"
        )
        val phonesAmount: () -> IntRange = { 1..Random.nextInt(1..4) }
        val randomContactsSource = (0L..25L).map { id ->
            ContactInfo(
                id,
                phones = phonesAmount().map { phoneSource.random() }.distinct(),
                name = null,
                photoInfo = null
            )
        }

        val grouped = MainActivity.groupBySimilar(randomContactsSource)

        println(randomContactsSource)
        println(grouped)
        println("Размер ${randomContactsSource.size}, сходств ${grouped.size}")

        return randomContactsSource
    }
}