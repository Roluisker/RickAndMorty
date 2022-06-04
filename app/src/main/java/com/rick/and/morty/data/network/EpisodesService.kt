package com.rick.and.morty.data.network

import com.rick.and.morty.data.model.episodes.EpisodesModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EpisodesService @Inject constructor(private val api: RickAndMortyApiClient) {

    suspend fun getEpisodes(episodesIds: String): EpisodesModel? {
        return withContext(Dispatchers.IO) {
            val response = api.getEpisodes(episodesIds)
            response.body() ?: null
        }
    }

}