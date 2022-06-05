package com.rick.and.morty.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rick.and.morty.R
import com.rick.and.morty.core.BaseFragment
import com.rick.and.morty.databinding.FragmentHomeBinding
import com.rick.and.morty.domain.model.character.CharacterInformation
import com.rick.and.morty.home.adapter.CharactersAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment(),
    CharactersAdapter.OnCharacterTouchListener {

    private lateinit var fragmentHomeBinding: FragmentHomeBinding
    private lateinit var characterAdapter: CharactersAdapter
    private val homeViewModel: HomeViewModel by viewModels()

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

        fragmentHomeBinding.lifecycleOwner = this
        return fragmentHomeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        characterAdapter = CharactersAdapter(
            this
        )

        fragmentHomeBinding.characters.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = characterAdapter
        }

        homeViewModel.characters.observe(lifecycleOwnerOwner()) {
            characterAdapter.addCharacters(it)
        }

        homeViewModel.isLoading.observe(lifecycleOwnerOwner()) {
            fragmentHomeBinding.isLoading = it
        }

        fragmentHomeBinding.characters.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!fragmentHomeBinding.characters.canScrollVertically(1)) {
                    homeViewModel.getAllCharacters()
                }
            }
        })
    }

    override fun onNetworkChange(isNetworkAvailable: Boolean) {
        if (isNetworkAvailable) {
            homeViewModel.getAllCharacters()
        } else {
            Log.d("Aloha", "no internet")
        }
    }

    override fun onTouchCharacter(character: CharacterInformation?) {
        character?.let {
            navController()?.navigate(HomeFragmentDirections.goToHomeCharacterDetail(it.id))
        }
    }

    override fun lifecycleOwnerOwner(): LifecycleOwner = fragmentHomeBinding.lifecycleOwner!!

}