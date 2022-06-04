package com.rick.and.morty.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.rick.and.morty.core.BaseFragment
import com.rick.and.morty.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private var _fragmentHomeBinding: FragmentHomeBinding? = null
    private val fragmentHomeBinding get() = _fragmentHomeBinding!!

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        homeViewModel.getAllCharacters()
        return fragmentHomeBinding.root
    }

}