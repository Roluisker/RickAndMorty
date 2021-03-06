package com.rick.and.morty.data.network

import com.rick.and.morty.data.model.episodes.EpisodesItem
import com.rick.and.morty.data.model.episodes.EpisodesModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EpisodesService @Inject constructor(private val api: RickAndMortyApiClient) {

    suspend fun getEpisodes(episodesIds: String): EpisodesModel? {
        return try {
            withContext(Dispatchers.IO) {
                val response = api.getEpisodes(episodesIds)
                response.body() ?: null
            }
        } catch (exception: Exception) {
            null
        }
    }

    suspend fun getEpisode(episodeId: String): EpisodesItem? {
        return try {
            withContext(Dispatchers.IO) {
                val response = api.getEpisode(episodeId)
                response.body() ?: null
            }
        } catch (exception: Exception) {
            null
        }
    }
}