package com.rick.and.morty.domain

import com.rick.and.morty.data.CharactersRepository
import com.rick.and.morty.domain.model.character.CharacterInformation
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetCharactersUseCaseTest {

    @RelaxedMockK
    private lateinit var charactersRepository: CharactersRepository
    lateinit var getCharactersUseCase: GetCharactersUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getCharactersUseCase = GetCharactersUseCase(charactersRepository)
    }

    @Test
    fun `when repository request characters from api`() = runBlocking {
        val myList = listOf(
            CharacterInformation(
                1, "Rick", "url",
                "species", "status", "gender", emptyList()
            )
        )

        val currentPage = 1

        coEvery { charactersRepository.getCharactersFromApi(currentPage) } returns myList

        val response = getCharactersUseCase(currentPage)
        assert(response == myList)
    }

    @Test
    fun `when repository request characters from api and the response is empty`() = runBlocking {
        val myList = emptyList<CharacterInformation>()
        val currentPage = 1

        coEvery { charactersRepository.getCharactersFromApi(currentPage) } returns myList

        val response = getCharactersUseCase(currentPage)
        assert(response == myList)
    }

}