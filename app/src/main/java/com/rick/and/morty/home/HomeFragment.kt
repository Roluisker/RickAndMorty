package com.rick.and.morty.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.rick.and.morty.R
import com.rick.and.morty.core.BaseFragment
import com.rick.and.morty.databinding.FragmentHomeBinding
import com.rick.and.morty.home.adapter.CharacterViewHolder
import com.rick.and.morty.home.adapter.CharactersAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment(), CharactersAdapter.CharacterTouchListener<CharacterViewHolder> {

    private lateinit var fragmentHomeBinding: FragmentHomeBinding
    private lateinit var characterAdapter: CharactersAdapter

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        characterAdapter = CharactersAdapter(
            this@HomeFragment
        )

        fragmentHomeBinding.characters.apply {
            setHasFixedSize(true)
            adapter = characterAdapter
        }

        homeViewModel.characters.observe(fragmentHomeBinding.lifecycleOwner!!) {
            characterAdapter.addCharacters(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentHomeBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_home, container, false
            )

        homeViewModel.getAllCharacters()

        fragmentHomeBinding.lifecycleOwner = this
        return fragmentHomeBinding.root
    }

    override fun onTouchCharacter(holder: CharacterViewHolder) {
        holder?.binding?.character?.name?.let { Log.d("Aloha", it) }
    }

}