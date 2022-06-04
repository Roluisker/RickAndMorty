package com.rick.and.morty.domain

import com.rick.and.morty.data.EpisodesRepository
import com.rick.and.morty.domain.model.episode.Episode
import javax.inject.Inject

class GetEpisodesUseCase @Inject constructor(private val episodesRepository: EpisodesRepository) {

    suspend operator fun invoke(episodesIds: List<String>): List<Episode> {
        val ids = StringBuilder()
        val episodesSize = episodesIds.size - 1

        episodesIds.forEachIndexed { index, episode ->
            val items = episode.split("/")
            ids.append(items[5])
            if (episodesSize != index) {
                ids.append(",")
            }
        }

        return episodesRepository.getEpisodesFromApi(ids.toString())
    }

}