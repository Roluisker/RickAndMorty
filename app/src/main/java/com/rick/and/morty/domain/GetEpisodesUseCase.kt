package com.rick.and.morty.domain

import com.rick.and.morty.data.EpisodesRepository
import com.rick.and.morty.domain.model.episode.Episode
import javax.inject.Inject

class GetEpisodesUseCase @Inject constructor(private val episodesRepository: EpisodesRepository) {

    suspend operator fun invoke(episodesIds: List<String>): List<Episode> {
        return episodesRepository.getEpisodesFromApi("1,2")
    }

}