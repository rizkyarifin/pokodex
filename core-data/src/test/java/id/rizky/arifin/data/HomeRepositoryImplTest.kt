package id.rizky.arifin.data

import app.cash.turbine.test
import com.nhaarman.mockitokotlin2.*
import com.skydoves.sandwich.ApiResponse
import id.rizky.arifin.core.data.repository.HomeRepositoryImpl
import id.rizky.arifin.core.network.model.PokemonResponse
import id.rizky.arifin.core.network.service.PokedexClient
import id.rizky.arifin.core.network.service.PokedexService
import id.rizky.arifin.core.test.MainCoroutinesRule
import id.rizky.arifin.core.test.MockData.mockPokemonList
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.toDuration

@ExperimentalCoroutinesApi
@ExperimentalTime
class HomeRepositoryImplTest {

    private lateinit var repository: HomeRepositoryImpl
    private lateinit var client: PokedexClient
    private val service: PokedexService = mock()

    @get:Rule
    val coroutinesRule = MainCoroutinesRule()

    @Before
    fun setup() {
        client = PokedexClient(service)
        repository = HomeRepositoryImpl(client, coroutinesRule.testDispatcher)
    }

    @Test
    fun fetchPokemonListFromNetworkTest() = runTest {
        val mockData =
            PokemonResponse(count = 1281, next = null, previous = null, results = mockPokemonList())
        whenever(service.fetchPokemonList()).thenReturn(ApiResponse.of { Response.success(mockData) })

        repository.fetchPokemonList(
            page = 0,
            onStart = {},
            onComplete = {},
            onError = {}
        ).test(2.toDuration(DurationUnit.SECONDS)) {
            val expectItem = awaitItem()[0]
            assertEquals(expectItem.page, 0)
            assertEquals(expectItem.name, "bulbasaur")
            assertEquals(expectItem, mockPokemonList()[0])
            awaitComplete()
        }

        verify(service, atLeastOnce()).fetchPokemonList()
        verifyNoMoreInteractions(service)
    }
}