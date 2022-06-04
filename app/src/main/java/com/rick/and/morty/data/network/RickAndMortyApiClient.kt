package com.rick.and.morty.data.network

import com.rick.and.morty.data.model.characters.CharactersModel
import com.rick.and.morty.data.model.characters.Result
import com.rick.and.morty.data.model.episodes.EpisodesModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApiClient {
    @GET("character")
    suspend fun getAllCharacters(@Query("page") page: Int): Response<CharactersModel>

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") characterId: Int): Response<Result>

    @GET("episode/{ids}")
    suspend fun getEpisodes(@Path("ids") episodesIds: String): Response<EpisodesModel>
}