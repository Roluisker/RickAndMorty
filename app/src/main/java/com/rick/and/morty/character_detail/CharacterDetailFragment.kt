package com.rick.and.morty.character_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.rick.and.morty.R
import com.rick.and.morty.core.BaseFragment
import com.rick.and.morty.databinding.FragmentDetailCharacterBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : BaseFragment() {

    private lateinit var fragmentDetailCharacterBinding: FragmentDetailCharacterBinding
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

        characterDetailViewModel.getCharacter(
            CharacterDetailFragmentArgs.fromBundle(
                requireArguments()
            ).characterId
        )

        characterDetailViewModel.character.observe(fragmentDetailCharacterBinding.lifecycleOwner!!) {
            Picasso.get()
                .load(it.imageUrl)
                .placeholder(R.drawable.place_holder)
                .into(fragmentDetailCharacterBinding.thumbnail)
            fragmentDetailCharacterBinding.character = it
        }

        characterDetailViewModel.isLoading.observe(fragmentDetailCharacterBinding.lifecycleOwner!!) {
            fragmentDetailCharacterBinding.isLoading = it
        }
    }

}