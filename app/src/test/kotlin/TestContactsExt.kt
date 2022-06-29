import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import ru.surfstudio.summerschool.MainActivity
import ru.surfstudio.summerschool.SimilarContactsPair
import ru.surfstudio.summerschool.data.ContactInfo
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
                id = 1,
                name = null,
                photoInfo = null,
                phones = listOf("79014146725", "79203622090", "79646634867")
            ),
            ContactInfo(
                id = 2,
                name = null,
                photoInfo = null,
                phones = listOf("79092028724", "79247823872")
            ),
            ContactInfo(id = 3, name = null, photoInfo = null, phones = listOf("79779020793")),
            ContactInfo(
                id = 4,
                name = null,
                photoInfo = null,
                phones = listOf("79545902502", "79685465291")
            ),
            ContactInfo(
                id = 5,
                name = null,
                photoInfo = null,
                phones = listOf("79013100153", "79865264496", "79678468246", "79036265459")
            ),
            ContactInfo(id = 6, name = null, photoInfo = null, phones = listOf("79013100153")),
            ContactInfo(
                id = 7,
                name = null,
                photoInfo = null,
                phones = listOf("79354859924", "79354859924", "79211298931")
            ),
            ContactInfo(
                id = 8,
                name = null,
                photoInfo = null,
                phones = listOf("79524809145", "79953275445", "79257477937", "79111236854")
            ),
            ContactInfo(
                id = 9,
                name = null,
                photoInfo = null,
                phones = listOf("79715864627", "79678468246", "79224225121", "79149048078")
            ),
            ContactInfo(
                id = 10,
                name = null,
                photoInfo = null,
                phones = listOf("79098379361", "79203622090", "79052740758")
            ),
            ContactInfo(
                id = 11,
                name = null,
                photoInfo = null,
                phones = listOf("79918336173", "79598875349")
            ),
            ContactInfo(
                id = 12,
                name = null,
                photoInfo = null,
                phones = listOf("79605337137", "79715864627", "79685465291")
            ),
            ContactInfo(
                id = 13,
                name = null,
                photoInfo = null,
                phones = listOf("79247823872", "79013100153", "79254820868", "79943131182")
            ),
            ContactInfo(
                id = 14,
                name = null,
                photoInfo = null,
                phones = listOf("79769986598", "79943131182", "79954206504")
            ),
            ContactInfo(
                id = 15,
                name = null,
                photoInfo = null,
                phones = listOf("79224225121", "79149048078", "79045977704")
            ),
            ContactInfo(
                id = 16,
                name = null,
                photoInfo = null,
                phones = listOf("79598875349", "79524809145", "79867167340")
            ),
            ContactInfo(id = 17, name = null, photoInfo = null, phones = listOf("79679358882")),
            ContactInfo(
                id = 18,
                name = null,
                photoInfo = null,
                phones = listOf("79954206504", "79961446714")
            ),
            ContactInfo(
                id = 19,
                name = null,
                photoInfo = null,
                phones = listOf("79953275445", "79315934585", "79646634867")
            ),
            ContactInfo(
                id = 20,
                name = null,
                photoInfo = null,
                phones = listOf("79598950711", "79026136334", "79598875349", "79954206504")
            ),
            ContactInfo(
                id = 21,
                name = null,
                photoInfo = null,
                phones = listOf("79779020793", "79045977704")
            ),
            ContactInfo(
                id = 22,
                name = null,
                photoInfo = null,
                phones = listOf("79014146725", "79943131182")
            ),
            ContactInfo(
                id = 23,
                name = null,
                photoInfo = null,
                phones = listOf("79835351000", "79606223417", "79257477937")
            ),
            ContactInfo(id = 24, name = null, photoInfo = null, phones = listOf("79943131182")),
            ContactInfo(
                id = 25,
                name = null,
                photoInfo = null,
                phones = listOf("79715864627", "79315934585")
            ),
            ContactInfo(id = 26, name = null, photoInfo = null, phones = listOf("79835351000")),
            ContactInfo(
                id = 27,
                name = null,
                photoInfo = null,
                phones = listOf("79013100153", "79598875349", "79598950711", "79224225121")
            ),
            ContactInfo(id = 28, name = null, photoInfo = null, phones = listOf("79865264496")),
            ContactInfo(
                id = 29,
                name = null,
                photoInfo = null,
                phones = listOf("79598950711", "79867167340")
            ),
            ContactInfo(
                id = 30,
                name = null,
                photoInfo = null,
                phones = listOf("79790715752", "79092028724", "79953275445", "79545902502")
            ),
            ContactInfo(
                id = 31,
                name = null,
                photoInfo = null,
                phones = listOf("79211298931", "79111236854", "79815863301")
            ),
            ContactInfo(
                id = 32,
                name = null,
                photoInfo = null,
                phones = listOf("79354859924", "79598950711", "79211298931")
            ),
            ContactInfo(
                id = 33,
                name = null,
                photoInfo = null,
                phones = listOf("79014146725", "79013100153", "79014146725", "79080671328")
            ),
            ContactInfo(id = 34, name = null, photoInfo = null, phones = listOf("79013100153")),
            ContactInfo(
                id = 35,
                name = null,
                photoInfo = null,
                phones = listOf("79545902502", "79045977704", "79779020793", "79098379361")
            ),
            ContactInfo(
                id = 36,
                name = null,
                photoInfo = null,
                phones = listOf("79224225121", "79052740758", "79257477937", "79598950711")
            ),
            ContactInfo(
                id = 37,
                name = null,
                photoInfo = null,
                phones = listOf("79396415540", "79606223417")
            ),
            ContactInfo(
                id = 38,
                name = null,
                photoInfo = null,
                phones = listOf("79013100153", "79613566857")
            ),
            ContactInfo(
                id = 39,
                name = null,
                photoInfo = null,
                phones = listOf("79685465291", "79715864627", "79111236854")
            ),
            ContactInfo(
                id = 40,
                name = null,
                photoInfo = null,
                phones = listOf("79855583032", "79524809145", "79613566857")
            ),
            ContactInfo(
                id = 41,
                name = null,
                photoInfo = null,
                phones = listOf("79779020793", "79961446714", "79954206504")
            ),
            ContactInfo(
                id = 42,
                name = null,
                photoInfo = null,
                phones = listOf("79779020793", "79203622090", "79203622090", "79865264496")
            ),
            ContactInfo(
                id = 43,
                name = null,
                photoInfo = null,
                phones = listOf("79779020793", "79014146725", "79036265459", "79598950711")
            ),
            ContactInfo(
                id = 44,
                name = null,
                photoInfo = null,
                phones = listOf("79396415540", "79953275445")
            ),
            ContactInfo(
                id = 45,
                name = null,
                photoInfo = null,
                phones = listOf("79790715752", "79961446714")
            ),
            ContactInfo(
                id = 46,
                name = null,
                photoInfo = null,
                phones = listOf("79943131182", "79354859924")
            )
        )
        val result = listOf(
            SimilarContactsPair(
                contact = ContactInfo(
                    id = 10,
                    name = null,
                    photoInfo = null,
                    phones = listOf("79098379361", "79203622090", "79052740758")
                ),
                similarContact = listOf(
                    ContactInfo(
                        id = 1,
                        name = null,
                        photoInfo = null,
                        phones = listOf("79014146725", "79203622090", "79646634867")
                    )
                )
            ),
            SimilarContactsPair(
                contact = ContactInfo(
                    id = 19,
                    name = null,
                    photoInfo = null,
                    phones = listOf("79953275445", "79315934585", "79646634867")
                ),
                similarContact = listOf(
                    ContactInfo(
                        id = 1,
                        name = null,
                        photoInfo = null,
                        phones = listOf("79014146725", "79203622090", "79646634867")
                    )
                )
            ),
            SimilarContactsPair(
                contact = ContactInfo(
                    id = 22,
                    name = null,
                    photoInfo = null,
                    phones = listOf("79014146725", "79943131182")
                ),
                similarContact = listOf(
                    ContactInfo(
                        id = 1,
                        name = null,
                        photoInfo = null,
                        phones = listOf("79014146725", "79203622090", "79646634867")
                    )
                )
            ),
            SimilarContactsPair(
                contact = ContactInfo(
                    id = 33,
                    name = null,
                    photoInfo = null,
                    phones = listOf("79014146725", "79013100153", "79014146725", "79080671328")
                ),
                similarContact = listOf(
                    ContactInfo(
                        id = 1,
                        name = null,
                        photoInfo = null,
                        phones = listOf("79014146725", "79203622090", "79646634867")
                    )
                )
            ),
            SimilarContactsPair(
                contact = ContactInfo(
                    id = 42,
                    name = null,
                    photoInfo = null,
                    phones = listOf("79779020793", "79203622090", "79203622090", "79865264496")
                ),
                similarContact = listOf(
                    ContactInfo(
                        id = 1,
                        name = null,
                        photoInfo = null,
                        phones = listOf("79014146725", "79203622090", "79646634867")
                    )
                )
            ),
            SimilarContactsPair(
                contact = ContactInfo(
                    id = 43,
                    name = null,
                    photoInfo = null,
                    phones = listOf("79779020793", "79014146725", "79036265459", "79598950711")
                ),
                similarContact = listOf(
                    ContactInfo(
                        id = 1,
                        name = null,
                        photoInfo = null,
                        phones = listOf("79014146725", "79203622090", "79646634867")
                    )
                )
            )
        )
        // assertThat(MainActivity.groupBySimilar(source), equalTo(result))
        getRandomContactsData()
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
            "79026136334", "79646634867", "79257477937", "79918336173", "79014146725"
        )
        val phonesAmount: () -> IntRange = { 1..Random.nextInt(1..4) }
        val randomContactsSource = (5..50).map {
            ContactInfo(
                (it - 4L),
                phones = phonesAmount().map { phoneSource.random() },
                name = null,
                photoInfo = null
            )
        }

        val grouped = MainActivity.groupBySimilar(randomContactsSource)
            .map { (it.contact.id) to it.similarPhones }
        println(grouped)
        println("Размер ${randomContactsSource.size}, сходств ${grouped.size}")

        return randomContactsSource
    }
}