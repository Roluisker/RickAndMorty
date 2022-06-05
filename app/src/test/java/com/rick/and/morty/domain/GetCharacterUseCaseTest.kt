package com.rick.and.morty.domain

import com.rick.and.morty.data.CharactersRepository
import com.rick.and.morty.domain.model.character.CharacterInformation
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetCharacterUseCaseTest {

    @RelaxedMockK
    private lateinit var charactersRepository: CharactersRepository
    lateinit var getCharacterUseCase: GetCharacterUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getCharacterUseCase = GetCharacterUseCase(charactersRepository)
    }

    @Test
    fun `when repository request characters from api`() = runBlocking {
        val character = CharacterInformation(
            1, "Rick", "url",
            "species", "status", "gender", emptyList()
        )

        val characterId = 1

        coEvery { charactersRepository.getCharacterFromApi(characterId) } returns character

        val response = getCharacterUseCase(characterId)
        assert(response == character)
    }

    @Test
    fun `when repository request characters from api and the response is null`() = runBlocking {
        val characterId = 1

        coEvery { charactersRepository.getCharacterFromApi(characterId) } returns null

        val response = getCharacterUseCase(characterId)
        assert(response == null)
    }

}