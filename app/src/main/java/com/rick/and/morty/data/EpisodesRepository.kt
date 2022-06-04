package com.rick.and.morty.data

import com.rick.and.morty.data.model.episodes.EpisodesModel
import com.rick.and.morty.data.model.episodes.toEpisode
import com.rick.and.morty.data.network.EpisodesService
import com.rick.and.morty.domain.model.episode.Episode
import javax.inject.Inject

class EpisodesRepository @Inject constructor(private val api: EpisodesService) {

    suspend fun getEpisodesFromApi(episodesIds: String): List<Episode> {
        val episodesModel: EpisodesModel? = api.getEpisodes(episodesIds)
        val episodes = mutableListOf<Episode>()

        episodesModel?.forEach { it ->
            episodes.add(it.toEpisode())
        }

        return episodes
    }

}