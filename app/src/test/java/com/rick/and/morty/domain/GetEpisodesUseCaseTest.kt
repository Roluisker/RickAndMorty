package com.rick.and.morty.domain

import com.rick.and.morty.data.EpisodesRepository
import com.rick.and.morty.domain.model.episode.Episode
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetEpisodesUseCaseTest {

    @RelaxedMockK
    private lateinit var episodesRepository: EpisodesRepository
    lateinit var getEpisodesUseCase: GetEpisodesUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getEpisodesUseCase = GetEpisodesUseCase(episodesRepository)
    }

    @Test
    fun `when repository request episodes from api`() = runBlocking {
        val myList = listOf(
            Episode(
                1, "Rick", "2012"
            )
        )

        val episodesIds = "1,2"
        val episodesListIds = listOf(
            "https://rickandmortyapi.com/api/episode/1",
            "https://rickandmortyapi.com/api/episode/2"
        )

        coEvery { episodesRepository.getEpisodesFromApi(episodesIds) } returns myList

        val response = getEpisodesUseCase(episodesListIds)
        assert(response == myList)
    }

}