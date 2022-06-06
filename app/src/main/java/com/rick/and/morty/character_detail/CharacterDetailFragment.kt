package com.rick.and.morty.character_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.rick.and.morty.R
import com.rick.and.morty.character_detail.adapter.EpisodesAdapter
import com.rick.and.morty.core.BaseFragment
import com.rick.and.morty.core.ui.showSnackBar
import com.rick.and.morty.databinding.FragmentDetailCharacterBinding
import com.rick.and.morty.domain.model.character.CharacterInformation
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : BaseFragment() {

    private lateinit var fragmentDetailCharacterBinding: FragmentDetailCharacterBinding
    private lateinit var episodesAdapter: EpisodesAdapter
    private lateinit var currentCharacter: CharacterInformation
    private val characterDetailViewModel: CharacterDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentDetailCharacterBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_detail_character, container, false
            )

        fragmentDetailCharacterBinding.lifecycleOwner = this
        return fragmentDetailCharacterBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        episodesAdapter = EpisodesAdapter()

        fragmentDetailCharacterBinding.episodes.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = episodesAdapter
        }

        characterDetailViewModel.episodes.observe(lifecycleOwnerOwner()) {
            episodesAdapter.addEpisodes(it)
        }

        characterDetailViewModel.character.observe(lifecycleOwnerOwner()) {
            currentCharacter = it
            characterDetailViewModel.getEpisodes(currentCharacter.episodes)
            Picasso.get()
                .load(currentCharacter.imageUrl)
                .placeholder(R.drawable.place_holder)
                .into(fragmentDetailCharacterBinding.thumbnail)
            fragmentDetailCharacterBinding.character = currentCharacter
        }

        characterDetailViewModel.isLoading.observe(lifecycleOwnerOwner()) {
            fragmentDetailCharacterBinding.isLoading = it
        }

        characterDetailViewModel.isDataDisplayable.observe(lifecycleOwnerOwner()) {
            fragmentDetailCharacterBinding.isDataDisplayable = it
        }

        fragmentDetailCharacterBinding.characterVideoButton.setOnClickListener {
            navController()?.navigate(
                CharacterDetailFragmentDirections.goToCharacterVideo(
                    getString(
                        R.string.default_character_video
                    ),
                    currentCharacter.name
                )
            )
        }
    }

    override fun onNetworkChange(isNetworkAvailable: Boolean) {
        if (isNetworkAvailable) {
            fragmentDetailCharacterBinding.characterVideoButton.visibility = View.VISIBLE

            characterDetailViewModel.getCharacter(
                CharacterDetailFragmentArgs.fromBundle(
                    requireArguments()
                ).characterId
            )

        } else {
            fragmentDetailCharacterBinding.characterVideoButton.visibility = View.GONE
            showSnackBar(
                fragmentDetailCharacterBinding.root, Snackbar.LENGTH_INDEFINITE,
                10000, "Offline"
            )
        }
    }

    override fun lifecycleOwnerOwner(): LifecycleOwner =
        fragmentDetailCharacterBinding.lifecycleOwner!!

}