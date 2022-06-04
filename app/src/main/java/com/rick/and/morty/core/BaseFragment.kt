package com.rick.and.morty.core

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation

open class BaseFragment : Fragment() {
    fun navController(): NavController? {
        return view?.let { Navigation.findNavController(it) }
    }
}