package com.rick.and.morty.domain

import com.rick.and.morty.data.EpisodesRepository
import com.rick.and.morty.domain.model.episode.Episode
import javax.inject.Inject

class GetEpisodeUseCase @Inject constructor(private val episodesRepository: EpisodesRepository) {

    suspend operator fun invoke(episodesIds: String): Episode? {
        val episodeId = episodesIds.split("/")[5]
        return episodesRepository.getEpisodeFromApi(episodeId)
    }

}