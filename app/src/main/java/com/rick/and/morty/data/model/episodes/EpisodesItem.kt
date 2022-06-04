package com.rick.and.morty.data.model.episodes

import com.rick.and.morty.domain.model.episode.Episode

data class EpisodesItem(
    val air_date: String,
    val characters: List<String>,
    val created: String,
    val episode: String,
    val id: Int,
    val name: String,
    val url: String
)

fun EpisodesItem.toEpisode(): Episode {
    return Episode(
        id = id,
        name = name,
        airDate = air_date
    )
}
