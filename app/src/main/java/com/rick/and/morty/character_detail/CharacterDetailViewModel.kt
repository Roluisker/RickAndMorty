package com.rick.and.morty.character_detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.exoplayer.SimpleExoPlayer
import com.rick.and.morty.domain.GetCharacterUseCase
import com.rick.and.morty.domain.GetEpisodeUseCase
import com.rick.and.morty.domain.GetEpisodesUseCase
import com.rick.and.morty.domain.model.character.CharacterInformation
import com.rick.and.morty.domain.model.episode.Episode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getCharacterUseCase: GetCharacterUseCase,
    private val getEpisodesUseCase: GetEpisodesUseCase,
    private val getEpisodeUseCase: GetEpisodeUseCase
) :
    ViewModel() {

    val character = MutableLiveData<CharacterInformation>()

    private var currentEpisodes = ArrayList<Episode>()
    val episodes = MutableLiveData<ArrayList<Episode>>()

    val isLoading = MutableLiveData(false)
    val isDataDisplayable = MutableLiveData(false)

    fun getCharacter(characterId: Int) {
        isLoading.postValue(true)
        viewModelScope.launch {
            val characterResult = getCharacterUseCase(characterId)

            characterResult?.let {
                character.postValue(it)
            } ?: run {
                Log.d("CharacterDetailViewModel", "null")
            }
            isLoading.postValue(false)
        }
    }

    fun getEpisodes(episodesUrls: List<String>) {
        when {
            episodesUrls.size > 1 -> {
                launchEpisodes(episodesUrls)
            }
            episodesUrls.size == 1 -> {
                launchEpisode(episodesUrls.first())
            }
        }
    }

    private fun launchEpisode(episodeUrl: String) {
        isLoading.postValue(true)
        viewModelScope.launch {
            val episode = getEpisodeUseCase(episodeUrl)

            episode?.let {
                currentEpisodes.add(it)
                episodes.postValue(currentEpisodes)
            } ?: run {
                Log.d("CharacterDetailViewModel", "null")
            }
            isLoading.postValue(false)
            isDataDisplayable.postValue(true)
        }
    }

    private fun launchEpisodes(episodesIdes: List<String>) {
        isLoading.postValue(true)
        viewModelScope.launch {
            val episodesResult = getEpisodesUseCase(episodesIdes)

            if (!episodesResult.isNullOrEmpty()) {
                currentEpisodes.addAll(episodesResult)
                episodes.postValue(currentEpisodes)
            }
            isLoading.postValue(false)
            isDataDisplayable.postValue(true)
        }
    }

}