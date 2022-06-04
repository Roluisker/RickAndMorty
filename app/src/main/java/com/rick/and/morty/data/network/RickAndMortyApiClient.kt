package com.rick.and.morty.data.network

import com.rick.and.morty.data.model.CharactersModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyApiClient {
    @GET("character")
    suspend fun getAllCharacters(@Query("page") page: Int): Response<CharactersModel>
}