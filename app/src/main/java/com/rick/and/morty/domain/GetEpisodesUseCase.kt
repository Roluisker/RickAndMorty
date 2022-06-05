package com.rick.and.morty.domain

import com.rick.and.morty.data.EpisodesRepository
import com.rick.and.morty.domain.model.episode.Episode
import javax.inject.Inject

class GetEpisodesUseCase @Inject constructor(private val episodesRepository: EpisodesRepository) {

    suspend operator fun invoke(episodesUrls: List<String>): List<Episode> {
        val ids = StringBuilder()

        episodesUrls.forEachIndexed { index, episode ->
            val items = episode.split("/")
            ids.append(items[5])
            if (episodesUrls.size - 1 != index) {
                ids.append(",")
            }
        }

        return episodesRepository.getEpisodesFromApi(ids.toString())
    }

}