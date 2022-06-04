package com.rick.and.morty.character_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.rick.and.morty.R
import com.rick.and.morty.core.BaseFragment
import com.rick.and.morty.databinding.FragmentDetailCharacterBinding

class CharacterDetailFragment : BaseFragment() {

    private lateinit var fragmentDetailCharacterBinding: FragmentDetailCharacterBinding

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

}